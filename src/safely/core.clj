(ns safely.core)

(defmacro safely 
  [& exprs]
  (let [fst#     (first exprs)
        opts#    (if (map? fst#) fst#)
        exprs#   (if (nil? opts#) exprs (rest exprs))
        nada#    (fn [& s] identity nil)
        hdl#     (if-not (nil? opts#) (:with opts#) nada#)]
    `(try
        (do ~@exprs#)
      (catch Exception e# (~hdl# e#)))))

