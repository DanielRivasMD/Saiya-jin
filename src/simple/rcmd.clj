;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rcmd

  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "rcmd.edn")

(def hc-jump-save            ["save_selection"])
(def hc-jump-backqard        ["jump_backqard"])
(def hc-jump-picker          ["jumplist_picker"])
(def hc-jump-forward         ["jump_forward"])

(defn rcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [c/kqp_al      [c/kq_al]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_ar      [c/kq_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_au      [c/kq_au]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_ad      [c/kq_ad]]

    ^{:doc/actions [{}]}                                                                           [c/kqrp_al     [c/kqr_al]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_ar     [c/kqr_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_au     [c/kqr_au]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_ad     [c/kqr_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %<+% `"}]}       [c/kqp_ob      [c/k_sp c/ks_5 c/ks_cm c/ks_eq c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %+>% `"}]}       [c/kqp_cb      [c/k_sp c/ks_5 c/ks_eq c/ks_pe c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %$% `"}]}        [c/kqp_sc      [c/k_sp c/ks_5 c/ks_4 c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %T>% `"}]}       [c/kqp_qu      [c/k_sp c/ks_5 c/ks_t c/ks_pe c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %<>% `"}]}       [c/kqp_bl      [c/k_sp c/ks_5 c/ks_cm c/ks_pe c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %<% `"}]}        [c/kqp_cm      [c/k_sp c/ks_5 c/ks_cm c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %>% `"}]}        [c/kqp_pe      [c/k_sp c/ks_5 c/ks_pe c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %!>% `"}]}       [c/kqp_sl      [c/k_sp c/ks_5 c/ks_1 c/ks_pe c/ks_5 c/k_sp]]

    ^{:doc/actions [{}]}                                                                           [c/kqrp_ob     [c/kqr_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_cb     [c/kqr_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_sc     [c/kqr_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_qu     [c/kqr_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_bl     [c/kqr_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_cm     [c/kqr_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_pe     [c/kqr_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_sl     [c/kqr_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [c/kqp_db      [c/kq_db]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_re      [c/kq_re]]
    ^{:doc/actions [{:program c/hc,    :action "save jump",          :exec hc-jump-save}]}         [c/kqp_sp      [:!TSf18]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump forward",       :exec hc-jump-forward}]}      [c/kqp_lc      [:!TSend]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jumplist picker",    :exec hc-jump-picker}]}       [c/kqp_lo      [:!TSf12]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump backqard",      :exec hc-jump-backqard}]}     [c/kqp_lt      [:!TShome]     c/term]
    ^{:doc/actions [{}]}                                                                           [c/kqp_rt      [c/kq_rt]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_ls      [c/kq_ls]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_esc     [c/kq_esc]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_tab     [c/kq_tab]]

    ^{:doc/actions [{}]}                                                                           [c/kqrp_db     [c/kqr_db]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_re     [c/kqr_re]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_sp     [c/kqr_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_lc     [c/kqr_lc]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_lo     [c/kqr_lo]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_lt     [c/kqr_lt]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_rt     [c/kqr_rt]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_ls     [c/kqr_ls]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_esc    [c/kqr_esc]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_tab    [c/kqr_tab]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kqp_1       [c/kq_1]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_2       [c/kq_2]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_3       [c/kq_3]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_4       [c/kq_4]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_5       [c/kq_5]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_6       [c/kq_6]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_7       [c/kq_7]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_8       [c/kq_8]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_9       [c/kq_9]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_0       [c/kq_0]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_hy      [c/kq_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_eq      [c/kq_eq]]

    ^{:doc/actions [{}]}                                                                           [c/kqrp_1      [c/kqr_1]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_2      [c/kqr_2]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_3      [c/kqr_3]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_4      [c/kqr_4]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_5      [c/kqr_5]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_6      [c/kqr_6]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_7      [c/kqr_7]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_8      [c/kqr_8]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_9      [c/kqr_9]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_0      [c/kqr_0]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_hy     [c/kqr_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_eq     [c/kqr_eq]]

   ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [c/kqp_a       [c/kq_a]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_b       [c/kq_b]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_c       [c/kq_c]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_d       [c/kq_d]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_e       [c/kq_e]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_f       [c/kq_f]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_g       [c/kq_g]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_h       [c/kq_h]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_i       [c/kq_i]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_j       [c/kq_j]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_k       [c/kq_k]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_l       [c/kq_l]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_m       [c/kq_m]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_n       [c/kq_n]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_o       [c/kq_o]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_p       [c/kq_p]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_q       [c/kq_q]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_r       [c/kq_r]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_s       [c/kq_s]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_t       [c/kq_t]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_u       [c/kq_u]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_v       [c/kq_v]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_w       [c/kq_w]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_x       [c/kq_x]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_y       [c/kq_y]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_z       [c/kq_z]]
    ^{:doc/actions [{}]}                                                                           [c/kqp_rt      [c/kq_rt]]

    ^{:doc/actions [{}]}                                                                           [c/kqrp_a      [c/kqr_a]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_b      [c/kqr_b]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_c      [c/kqr_c]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_d      [c/kqr_d]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_e      [c/kqr_e]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_f      [c/kqr_f]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_g      [c/kqr_g]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_h      [c/kqr_h]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_i      [c/kqr_i]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_j      [c/kqr_j]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_k      [c/kqr_k]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_l      [c/kqr_l]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_m      [c/kqr_m]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_n      [c/kqr_n]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_o      [c/kqr_o]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_p      [c/kqr_p]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_q      [c/kqr_q]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_r      [c/kqr_r]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_s      [c/kqr_s]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_t      [c/kqr_t]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_u      [c/kqr_u]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_v      [c/kqr_v]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_w      [c/kqr_w]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_x      [c/kqr_x]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_y      [c/kqr_y]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_z      [c/kqr_z]]
    ^{:doc/actions [{}]}                                                                           [c/kqrp_rt     [c/kqr_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
