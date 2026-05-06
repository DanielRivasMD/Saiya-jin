;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rcmd
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

(def out-file "rcmd.edn")

(defn rcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [r/kqp_al      [r/kq_al]]
    ^{:doc/actions [{}]}                                                                           [r/kqp_ar      [r/kq_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kqp_au      [r/kq_au]]
    ^{:doc/actions [{}]}                                                                           [r/kqp_ad      [r/kq_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm,    :action "left assign",        :sequence "` <- `"}]}         [t/kqp_ob      [a/k_sp t/ks_cm n/k_hy a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "right assign",       :sequence "` -> `"}]}         [t/kqp_cb      [a/k_sp n/k_hy t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "variable expansion", :sequence "`${}`"}]}          [t/kqp_sc      [n/ks_4 t/ks_ob]]
    ^{:doc/actions [{:program c/tm,    :action "command subs",       :sequence "`$()`"}]}          [t/kqp_qu      [n/ks_4 n/ks_9]]
    ^{:doc/actions [{:program c/tm,    :action "process subs",       :sequence "`<()`"}]}          [t/kqp_bl      [t/ks_cm n/ks_9]]
    ^{:doc/actions [{:program c/tm,    :action "rust attribute",     :sequence "`#[]`"}]}          [t/kqp_cm      [n/ks_3 t/k_ob]]
    ^{:doc/actions [{:program c/tm,    :action "rust documentation", :sequence "`//! `"}]}         [t/kqp_pe      [t/k_sl t/k_sl n/ks_1 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "shebang",            :sequence "`#!/`"}]}          [t/kqp_sl      [n/ks_3 n/ks_1 t/k_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/kqp_db      [a/kq_db]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_re      [a/kq_re]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_rs      [a/kq_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_sp      [a/kq_sp]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_lc      [a/kq_lc]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_lo      [a/kq_lo]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_lt      [a/kq_lt]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_ls      [a/kq_ls]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_caps    [a/kq_caps]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_tab     [a/kq_tab]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kqp_1       [n/kq_1]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_2       [n/kq_2]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_3       [n/kq_3]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_4       [n/kq_4]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_5       [n/kq_5]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_6       [n/kq_6]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_7       [n/kq_7]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_8       [n/kq_8]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_9       [n/kq_9]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_0       [n/kq_0]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_hy      [n/kq_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kqp_eq      [n/kq_eq]]

   ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [b/kqp_a       [b/kq_a]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_b       [b/kq_b]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_c       [b/kq_c]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_d       [b/kq_d]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_e       [b/kq_e]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_f       [b/kq_f]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_g       [b/kq_g]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_h       [b/kq_h]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_i       [b/kq_i]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_j       [b/kq_j]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_k       [b/kq_k]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_l       [b/kq_l]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_m       [b/kq_m]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_n       [b/kq_n]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_o       [b/kq_o]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_p       [b/kq_p]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_q       [b/kq_q]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_r       [b/kq_r]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_s       [b/kq_s]]
    ^{:doc/actions [{:program c/tm,    :action "todo",        :sequence "TODO: "}]}                [b/kqp_t       [b/ks_t b/ks_o b/ks_d b/ks_o t/ks_sc a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_u       [b/kq_u]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_v       [b/kq_v]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_w       [b/kq_w]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_x       [b/kq_x]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_y       [b/kq_y]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_z       [b/kq_z]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_rt      [b/kq_rt]]
  	]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
