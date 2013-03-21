# safely

A Clojure library for executing expressions safely. 

## Usage

To use in Leiningen:

    [safely "1.0.0"]

Running the following:

    (safely
      (throw (Exception. "Hello, world")))

is the equivalent to running:
        
    (try
      (throw (Exception. "Hello, world"))
    (catch Exception e))

Which is to say, ignore any exceptions.

Each expression is wrapped in a separate try/catch block. So:

    (safely
      (throw (Exception. "foo"))
      (throw (Exception. "bar")))

Expands to:

    (try
      (throw (Exception. "foo"))
    (catch Exception e))

    (try
      (throw (Exception. "bar"))
    (catch Exception e))

Sometimes you want to do a little more with your exceptions:

    (defn warning [e]
      (let [*out* *err*]
        (println "[WARNING]" (.getMessage e))))

    (defn report [e]
      ; Report error to server
      )

    (safely {:with warning}
      (throw (Exception. "I result in a warning"))
      (throw (Exception. "And an additional warning")))

    ; Prints "[WARNING] I result in a warning"
    ; Prints "[WARNING] And an additional warning"

    (safely {:with report}
      (throw (Exception. "I result in a report")))

    ; Reports exception to server

    (safely (throw Exception. "I am ignored"))

## License

Distributed under [Eclipse Public License 1.0](http://opensource.org/licenses/eclipse-1.0.php), which is the same as Clojure.

