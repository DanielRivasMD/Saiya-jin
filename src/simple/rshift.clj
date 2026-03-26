;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SHIFT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rshift
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
            [config.function :as f]
))

(def out-file "rshift.edn")

(def hc-goto-line            ["goto_line"])
(def hc-goto-column          ["goto_column"])
(def hc-last-mod             ["goto_last_modification"])

(def hc-del-char-back        ["delete_char_backward"])
(def hc-del-word-back        ["delete_word_backward"])
(def mc-del-word-back        ["DeleteWordLeft"])
(def hc-del-start-line       ["kill_to_line_start"])
(def mc-del-start-line       ["SelectToStartOfLine,Delete"])

(defn rshift []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Shift Mode"
   :rules
   [;
    ^{:doc/actions [{}]}                                                                           [r/krp_al      [r/kr_al]]
    ^{:doc/actions [{}]}                                                                           [r/krp_ar      [r/kr_ar]]
    ^{:doc/actions [{}]}                                                                           [r/krp_au      [r/kr_au]]
    ^{:doc/actions [{}]}                                                                           [r/krp_ad      [r/kr_ad]]

    ^{:doc/actions [{:program c/hc,    :action "last modification",  :exec hc-last-mod}]}          [a/krp_sp      [f/ko_f17]]
    ^{:doc/actions [{:program c/hc,    :action "delete line start",  :exec hc-del-start-line}
                    {:program c/mc,    :action "delete line start",  :exec mc-del-start-line}]}    [a/krp_lc      [c/kw_u]]
    ^{:doc/actions [{:program c/hc,    :action "goto column",        :exec hc-goto-column}]}       [a/krp_lo      [f/ks_f4]]
    ^{:doc/actions  [{:program c/hc,   :action "delete prev word",   :exec hc-del-word-back}
                     {:program c/mc,   :action "delete prev word",   :exec mc-del-word-back}]}     [a/krp_lt      [c/kw_w]]
    ^{:doc/actions [{:program c/hc,    :action "delete prev char",   :exec hc-del-char-back}]}     [a/krp_ls      [c/k_delb]]
    ^{:doc/actions [{:program c/hc,    :action "go to column",       :exec hc-goto-line}]}         [a/krp_esc     [f/ks_f5]]
    ^{:doc/actions [{}]}                                                                           [a/krp_tab     [a/kr_tab]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rshift)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
