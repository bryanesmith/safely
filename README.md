# safely

A Clojure library for executing expressions safely. 

## Usage

Running the following:

    (safely
      (throw (Exception. "Hello, world")))

is the equivalent to running:
        
    (try
      (throw (Exception. "Hello, world"))
    (catch Exception e))

Which is to say, ignore any exceptions.

Sometimes you want to do a little more with your exceptions:

    (defn warning [e]
      (let [*out* *err*]
        (println "[WARNING]" (.getMessage e))))

    (defn report [e]
      ; Report error to server
      )

    (safely {:with warning}
      (throw (Exception. "I result in a warning")))

    (safely {:with report}
      (throw (Exception. "I result in a report")))

## License

Need to figure this out.

