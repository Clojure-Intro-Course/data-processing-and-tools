(ns core
  (:use data_functions))


(defn -main
  "Type \"lein run p\" to print out all the information,
  or type \"lein run p00\" with a person's id number in the place of 00"
  [& args]
  (if (= (first (first args)) \p)
    (if (empty? (rest (first args)))
      (print-all)
      (print-select (apply str (rest (first args)))))
    nil))

