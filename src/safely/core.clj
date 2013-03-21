(ns safely.core)

; ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
(defmacro do-safely
  ([hdl expr] `(try ~expr
                (catch Exception e# (~hdl e#))))
  ([hdl expr & more]
    `(do
       (do-safely ~hdl ~expr)
       (do-safely ~hdl ~@more))))

; ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
(defmacro safely 
  "Safely run expressions, with an optional handler for exceptions."
  [& exprs]
  (let [fst#     (first exprs)
        opts#    (if (map? fst#) fst#)
        exprs#   (if (nil? opts#) exprs (rest exprs))
        nada#    (fn [& s] identity nil)
        hdl#     (if-not (nil? opts#) (:with opts#) nada#)]
    `(safely.core/do-safely ~hdl# ~@exprs#)))

