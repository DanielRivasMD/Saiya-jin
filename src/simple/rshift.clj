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

(def hc-last-mod             ["goto_last_modification"])
(def hc-del-char-back        ["delete_char_backward"])
(def hc-del-word-back        ["delete_word_backward"])
(def hc-del-start-line       ["kill_to_line_start"])

(def mc-del-word-back        ["DeleteWordLeft"])
(def mc-del-start-line       ["SelectToStartOfLine,Delete"])

(defn rshift []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Shift Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [r/krp_al      [r/kr_al]]
    ^{:doc/actions [{}]}                                                                           [r/krp_ar      [r/kr_ar]]
    ^{:doc/actions [{}]}                                                                           [r/krp_au      [r/kr_au]]
    ^{:doc/actions [{}]}                                                                           [r/krp_ad      [r/kr_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/krp_ob      [t/kr_ob]]
    ^{:doc/actions [{}]}                                                                           [t/krp_cb      [t/kr_cb]]
    ^{:doc/actions [{}]}                                                                           [t/krp_sc      [t/kr_sc]]
    ^{:doc/actions [{}]}                                                                           [t/krp_qu      [t/kr_qu]]
    ^{:doc/actions [{}]}                                                                           [t/krp_bl      [t/kr_bl]]
    ^{:doc/actions [{}]}                                                                           [t/krp_cm      [t/kr_cm]]
    ^{:doc/actions [{}]}                                                                           [t/krp_pe      [t/kr_pe]]
    ^{:doc/actions [{}]}                                                                           [t/krp_sl      [t/kr_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "last modification",  :exec hc-last-mod}]}          [a/krp_sp      [f/ko_f17]]
    ^{:doc/actions [{:program c/hc,    :action "delete line start",  :exec hc-del-start-line}
                    {:program c/mc,    :action "delete line start",  :exec mc-del-start-line}]}    [a/krp_lc      [c/kw_u]]
    ^{:doc/actions [{}]}                                                                           [a/krp_lo      [a/kr_lo]]
    ^{:doc/actions [{:program c/hc,    :action "delete prev word",   :exec hc-del-word-back}
                    {:program c/mc,    :action "delete prev word",   :exec mc-del-word-back}]}     [a/krp_lt      [c/kw_w]]
    ^{:doc/actions [{:program c/hc,    :action "delete prev char",   :exec hc-del-char-back}]}     [a/krp_ls      [c/k_delb]]
    ^{:doc/actions [{}]}                                                                           [a/krp_caps    [a/kr_caps]]
    ^{:doc/actions [{}]}                                                                           [a/krp_tab     [a/kr_tab]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/krp_1       [n/kr_1]]
    ^{:doc/actions [{}]}                                                                           [n/krp_2       [n/kr_2]]
    ^{:doc/actions [{}]}                                                                           [n/krp_3       [n/kr_3]]
    ^{:doc/actions [{}]}                                                                           [n/krp_4       [n/kr_4]]
    ^{:doc/actions [{}]}                                                                           [n/krp_5       [n/kr_5]]
    ^{:doc/actions [{}]}                                                                           [n/krp_6       [n/kr_6]]
    ^{:doc/actions [{}]}                                                                           [n/krp_7       [n/kr_7]]
    ^{:doc/actions [{}]}                                                                           [n/krp_8       [n/kr_8]]
    ^{:doc/actions [{}]}                                                                           [n/krp_9       [n/kr_9]]
    ^{:doc/actions [{}]}                                                                           [n/krp_0       [n/kr_0]]
    ^{:doc/actions [{}]}                                                                           [n/krp_hy      [n/kr_hy]]
    ^{:doc/actions [{}]}                                                                           [n/krp_eq      [n/kr_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/krp_a       [b/kr_a]]
    ^{:doc/actions [{}]}                                                                           [b/krp_b       [b/kr_b]]
    ^{:doc/actions [{}]}                                                                           [b/krp_c       [b/kr_c]]
    ^{:doc/actions [{}]}                                                                           [b/krp_d       [b/kr_d]]
    ^{:doc/actions [{}]}                                                                           [b/krp_e       [b/kr_e]]
    ^{:doc/actions [{}]}                                                                           [b/krp_f       [b/kr_f]]
    ^{:doc/actions [{}]}                                                                           [b/krp_g       [b/kr_g]]
    ^{:doc/actions [{}]}                                                                           [b/krp_h       [b/kr_h]]
    ^{:doc/actions [{}]}                                                                           [b/krp_i       [b/kr_i]]
    ^{:doc/actions [{}]}                                                                           [b/krp_j       [b/kr_j]]
    ^{:doc/actions [{}]}                                                                           [b/krp_k       [b/kr_k]]
    ^{:doc/actions [{}]}                                                                           [b/krp_l       [b/kr_l]]
    ^{:doc/actions [{}]}                                                                           [b/krp_m       [b/kr_m]]
    ^{:doc/actions [{}]}                                                                           [b/krp_n       [b/kr_n]]
    ^{:doc/actions [{}]}                                                                           [b/krp_o       [b/kr_o]]
    ^{:doc/actions [{}]}                                                                           [b/krp_p       [b/kr_p]]
    ^{:doc/actions [{}]}                                                                           [b/krp_q       [b/kr_q]]
    ^{:doc/actions [{}]}                                                                           [b/krp_r       [b/kr_r]]
    ^{:doc/actions [{}]}                                                                           [b/krp_s       [b/kr_s]]
    ^{:doc/actions [{}]}                                                                           [b/krp_t       [b/kr_t]]
    ^{:doc/actions [{}]}                                                                           [b/krp_u       [b/kr_u]]
    ^{:doc/actions [{}]}                                                                           [b/krp_v       [b/kr_v]]
    ^{:doc/actions [{}]}                                                                           [b/krp_w       [b/kr_w]]
    ^{:doc/actions [{}]}                                                                           [b/krp_x       [b/kr_x]]
    ^{:doc/actions [{}]}                                                                           [b/krp_y       [b/kr_y]]
    ^{:doc/actions [{}]}                                                                           [b/krp_z       [b/kr_z]]
    ^{:doc/actions [{}]}                                                                           [b/krp_rt      [b/kr_rt]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rshift)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
