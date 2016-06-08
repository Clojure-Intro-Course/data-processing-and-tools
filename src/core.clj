(ns core)

(defn -main [& args]
  (if (= (first args) "p")
      (load-file "src/data_functions.clj")
      nil))

