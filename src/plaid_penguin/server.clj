(ns plaid-penguin.server
  (:import [org.apache.thrift.server 
	    TServer 
	    TServer$Args 
	    TSimpleServer 
	    TThreadPoolServer
	    TThreadPoolServer$Args])
  (:import [org.apache.thrift.transport TServerSocket TServerTransport]))

(defn create-single-threaded [port processor]
  (let [transport (TServerSocket. port)]
    (TSimpleServer. (.processor (TServer$Args. transport) processor))))

(defn create-multi-threaded [port processor]
  (let [transport (TServerSocket. port)]
    (TThreadPoolServer. (.processor (TThreadPoolServer$Args. transport) processor))))
