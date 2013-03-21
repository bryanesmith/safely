(ns safely.core)

(defmacro do-safely [hdl & exprs]
  `(try
    (do ~@exprs)
  (catch Exception e# (~hdl e#))))

(defmacro safely 
  [& exprs]
  (let [fst#     (first exprs)
        opts#    (if (map? fst#) fst#)
        exprs#   (if (nil? opts#) exprs (rest exprs))
        nada#    (fn [& s] identity nil)
        hdl#     (if-not (nil? opts#) (:with opts#) nada#)]
    `(safely.core/do-safely ~hdl# ~@exprs#)))
