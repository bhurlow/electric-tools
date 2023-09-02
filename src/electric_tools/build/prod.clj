(ns electric-tools.build.prod
  "entrypoint for prod builds"
  (:require
   [electric-cli.server :as server]))

;; TODO
;; this could be configured via build arg
;; or by env var
(def electric-server-config
  {:host "0.0.0.0"
   :port 8080
   :resources-path "public"})

(defn -main [& args] ; run with `clj -M -m prod`
  (when (empty? (System/getProperty "HYPERFIDDLE_ELECTRIC_SERVER_VERSION"))
    (throw (ex-info "HYPERFIDDLE_ELECTRIC_SERVER_VERSION jvm property must be set in prod" {})))
  (server/start-server! electric-server-config))