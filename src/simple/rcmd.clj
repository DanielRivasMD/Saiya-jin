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

(def hc-jump-save            ["save_selection"])
(def hc-jump-backqard        ["jump_backward"])
(def hc-jump-picker          ["jumplist_picker"])
(def hc-jump-forward         ["jump_forward"])

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

    ^{:doc/actions [{}]}                                                                           [r/kqrp_al     [r/kqr_al]]
    ^{:doc/actions [{}]}                                                                           [r/kqrp_ar     [r/kqr_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kqrp_au     [r/kqr_au]]
    ^{:doc/actions [{}]}                                                                           [r/kqrp_ad     [r/kqr_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %<+% `"}]}       [t/kqp_ob      [a/k_sp n/ks_5 t/ks_cm n/ks_eq n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %+>% `"}]}       [t/kqp_cb      [a/k_sp n/ks_5 n/ks_eq t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %$% `"}]}        [t/kqp_sc      [a/k_sp n/ks_5 n/ks_4 n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %T>% `"}]}       [t/kqp_qu      [a/k_sp n/ks_5 b/ks_t t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %<>% `"}]}       [t/kqp_bl      [a/k_sp n/ks_5 t/ks_cm t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %<% `"}]}        [t/kqp_cm      [a/k_sp n/ks_5 t/ks_cm n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %>% `"}]}        [t/kqp_pe      [a/k_sp n/ks_5 t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %!>% `"}]}       [t/kqp_sl      [a/k_sp n/ks_5 n/ks_1 t/ks_pe n/ks_5 a/k_sp]]

    ^{:doc/actions [{}]}                                                                           [t/kqrp_ob     [t/kqr_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kqrp_cb     [t/kqr_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kqrp_sc     [t/kqr_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kqrp_qu     [t/kqr_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kqrp_bl     [t/kqr_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kqrp_cm     [t/kqr_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kqrp_pe     [t/kqr_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kqrp_sl     [t/kqr_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/kqp_db      [a/kq_db]]
    ^{:doc/actions [{}]}                                                                           [a/kqp_re      [a/kq_re]]
    ^{:doc/actions [{:program c/hc,    :action "save jump",          :exec hc-jump-save}]}         [a/kqp_sp      [f/ks_f14]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump forward",       :exec hc-jump-forward}]}      [a/kqp_lc      [f/ks_f15]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "jumplist picker",    :exec hc-jump-picker}]}       [a/kqp_lo      [f/ks_f16]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump backqard",      :exec hc-jump-backqard}]}     [a/kqp_lt      [f/ks_f17]     c/term]
    ^{:doc/actions [{}]}                                                                           [a/kqp_ls      [a/kq_ls]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_rt      [b/kq_rt]]

    ^{:doc/actions [{}]}                                                                           [a/kqrp_db     [a/kqr_db]]
    ^{:doc/actions [{}]}                                                                           [a/kqrp_re     [a/kqr_re]]
    ^{:doc/actions [{}]}                                                                           [a/kqrp_sp     [a/kqr_sp]]
    ^{:doc/actions [{}]}                                                                           [a/kqrp_lc     [a/kqr_lc]]
    ^{:doc/actions [{}]}                                                                           [a/kqrp_lo     [a/kqr_lo]]
    ^{:doc/actions [{}]}                                                                           [a/kqrp_lt     [a/kqr_lt]]
    ^{:doc/actions [{}]}                                                                           [a/kqrp_ls     [a/kqr_ls]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_rt     [b/kqr_rt]]

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

    ^{:doc/actions [{}]}                                                                           [n/kqrp_1      [n/kqr_1]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_2      [n/kqr_2]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_3      [n/kqr_3]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_4      [n/kqr_4]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_5      [n/kqr_5]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_6      [n/kqr_6]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_7      [n/kqr_7]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_8      [n/kqr_8]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_9      [n/kqr_9]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_0      [n/kqr_0]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_hy     [n/kqr_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kqrp_eq     [n/kqr_eq]]

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
    ^{:doc/actions [{}]}                                                                           [b/kqp_t       [b/kq_t]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_u       [b/kq_u]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_v       [b/kq_v]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_w       [b/kq_w]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_x       [b/kq_x]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_y       [b/kq_y]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_z       [b/kq_z]]
    ^{:doc/actions [{}]}                                                                           [b/kqp_rt      [b/kq_rt]]

    ^{:doc/actions [{}]}                                                                           [b/kqrp_a      [b/kqr_a]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_b      [b/kqr_b]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_c      [b/kqr_c]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_d      [b/kqr_d]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_e      [b/kqr_e]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_f      [b/kqr_f]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_g      [b/kqr_g]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_h      [b/kqr_h]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_i      [b/kqr_i]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_j      [b/kqr_j]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_k      [b/kqr_k]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_l      [b/kqr_l]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_m      [b/kqr_m]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_n      [b/kqr_n]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_o      [b/kqr_o]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_p      [b/kqr_p]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_q      [b/kqr_q]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_r      [b/kqr_r]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_s      [b/kqr_s]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_t      [b/kqr_t]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_u      [b/kqr_u]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_v      [b/kqr_v]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_w      [b/kqr_w]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_x      [b/kqr_x]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_y      [b/kqr_y]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_z      [b/kqr_z]]
    ^{:doc/actions [{}]}                                                                           [b/kqrp_rt     [b/kqr_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
