(ns safely.core)

(defmacro safely 
  [& exprs]
  `(try
      (do ~@exprs)
    (catch Exception e#)))
