(defproject plaid-penguin "1.0.0"
  :description "Library that makes it simple to define a thrift RPC interface in clojure."
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 [org.clojars.ithayer/thrift "0.6.1"]
		 [org.clojars.kjw/slf4j "1.5.5"]
		 [org.clojars.kjw/slf4j-simple "1.5.5"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
		     [lein-clojars "0.6.0"]])