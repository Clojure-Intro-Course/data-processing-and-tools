(ns core
  (:use data-functions))


(defn -main
  "1. Type \"lein run p\" to print out all the information in person by person format,
  or type \"lein run p00\" with a person's id number in the place of 00.
   2. Type \"lein run q\" to print out all the information in question by question format,
  or type \"lein run q00\" with a question number in the place of 00."
  [& args]
  (cond
    (= (first (first args)) \p)
      (if (empty? (rest (first args)))
        (print-all)
        (print-select (apply str (rest (first args)))))
    (= (first (first args)) \q)
      (if (empty? (rest (first args)))
        (print-all-questions)
        (let [q-name (apply str (rest (first args)))] (print-question (gather-question q-name) q-name)))
    :else "No argument or an invalid argument is given"))
