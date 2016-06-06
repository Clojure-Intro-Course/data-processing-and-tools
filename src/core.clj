(ns core)

(defn -main [& args]
  (if (= (first args) "people")
      (load-file "src/data_functions.clj")
      nil))
  ;(load-file "src/data_functions.clj"))

