(ns plaid-penguin.client
  (:import [org.apache.thrift.transport TSocket])
  (:import [org.apache.thrift.protocol TBinaryProtocol]))

(defn create-socket [hostname port]
  (let [transport (TSocket. hostname port)]
    (.open transport)
    transport))

(defmacro create [client-class transport]
  `(new ~client-class (TBinaryProtocol. ~transport)))