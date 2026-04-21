;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.ropt
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

(def out-file "ropt.edn")

(def hc-change-picker        ["changed_file_picker"])
(def hc-file-picker          ["file_explorer"])
(def hc-buffer-picker        ["buffer_picker"])
(def hc-jump-save            ["save_selection"])
(def hc-jump-backward        ["jump_backward"])
(def hc-jump-picker          ["jumplist_picker"])
(def hc-jump-forward         ["jump_forward"])

(defn ropt []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [r/kep_al      [r/ke_al]]
    ^{:doc/actions [{}]}                                                                           [r/kep_ar      [r/ke_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kep_au      [r/ke_au]]
    ^{:doc/actions [{}]}                                                                           [r/kep_ad      [r/ke_ad]]

    ^{:doc/actions [{}]}                                                                           [r/kerp_al     [r/ker_al]]
    ^{:doc/actions [{}]}                                                                           [r/kerp_ar     [r/ker_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kerp_au     [r/ker_au]]
    ^{:doc/actions [{}]}                                                                           [r/kerp_ad     [r/ker_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/R,     :action "tree update",        :sequence "` %<% `"}]}        [t/kep_ob      [a/k_sp n/ks_5 t/ks_cm n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "R pipe",             :sequence "` %>% `"}]}        [t/kep_cb      [a/k_sp n/ks_5 t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "assign pipe",        :sequence "` %<>% `"}]}       [t/kep_sc      [a/k_sp n/ks_5 t/ks_cm t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tee pipe",           :sequence "` %T>% `"}]}       [t/kep_qu      [a/k_sp n/ks_5 b/ks_t t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "exposition pipe",    :sequence "` %$% `"}]}        [t/kep_bl      [a/k_sp n/ks_5 n/ks_4 n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %<+% `"}]}       [t/kep_cm      [a/k_sp n/ks_5 t/ks_cm n/ks_eq n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tidyverse",          :sequence "` %!>% `"}]}       [t/kep_pe      [a/k_sp n/ks_5 n/ks_1 t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/R,     :action "tree update",        :sequence "` %+>% `"}]}       [t/kep_sl      [a/k_sp n/ks_5 n/ks_eq t/ks_pe n/ks_5 a/k_sp]]

    ^{:doc/actions [{}]}                                                                           [t/kerp_ob     [t/ker_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kerp_cb     [t/ker_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kerp_sc     [t/ker_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kerp_qu     [t/ker_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kerp_bl     [t/ker_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kerp_cm     [t/ker_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kerp_pe     [t/ker_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kerp_sl     [t/ker_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/tm,    :action "define",             :sequence "` => `"}]}         [a/kep_db      [a/k_sp n/k_eq t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "go assign",          :sequence "` := `"}]}         [a/kep_re      [a/k_sp t/ks_sc n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/sys,   :action "prompt espanso"}]}                                 [a/kep_sp      [a/ke_sp]]
    ^{:doc/actions [{:program c/hc,    :action "buffer picker",      :exec hc-buffer-picker}]}     [a/kep_lc      [b/kt_h]       c/term]
    ^{:doc/actions [{:program c/tm,    :action "atuin widget"}
                    {:program c/hc,    :action "file picker",        :exec hc-file-picker}]}       [a/kep_lo      [b/kt_j]       c/term]
    ^{:doc/actions [{:program c/tm,    :action "fzf history"}
                    {:program c/hc,    :action "change picker",      :exec hc-change-picker}]}     [a/kep_lt      [b/kt_o]       c/term]
    ^{:doc/actions [{}]}                                                                           [a/kep_ls      [a/ke_ls]]
    ^{:doc/actions [{}]}                                                                           [a/kep_caps    [a/ke_caps]]
    ^{:doc/actions [{}]}                                                                           [a/kep_tab     [a/ke_tab]]

    ^{:doc/actions [{}]}                                                                           [a/kerp_db     [a/ker_db]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_re     [a/ker_re]]
    ^{:doc/actions [{:program c/hc,    :action "save jump",          :exec hc-jump-save}]}         [a/kerp_sp     [f/ks_f14]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump forward",       :exec hc-jump-forward}]}      [a/kerp_lc     [f/ks_f15]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "jumplist picker",    :exec hc-jump-picker}]}       [a/kerp_lo     [f/ks_f16]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump backward",      :exec hc-jump-backward}]}     [a/kerp_lt     [f/ks_f17]     c/term]
    ^{:doc/actions [{}]}                                                                           [a/kerp_ls     [a/ker_ls]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_caps   [a/ker_caps]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_tab    [a/ker_tab]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kep_1       [n/ke_1]]
    ^{:doc/actions [{}]}                                                                           [n/kep_2       [n/ke_2]]
    ^{:doc/actions [{}]}                                                                           [n/kep_3       [n/ke_3]]
    ^{:doc/actions [{}]}                                                                           [n/kep_4       [n/ke_4]]
    ^{:doc/actions [{}]}                                                                           [n/kep_5       [n/ke_5]]
    ^{:doc/actions [{}]}                                                                           [n/kep_6       [n/ke_6]]
    ^{:doc/actions [{}]}                                                                           [n/kep_7       [n/ke_7]]
    ^{:doc/actions [{}]}                                                                           [n/kep_8       [n/ke_8]]
    ^{:doc/actions [{}]}                                                                           [n/kep_9       [n/ke_9]]
    ^{:doc/actions [{}]}                                                                           [n/kep_0       [n/ke_0]]
    ^{:doc/actions [{}]}                                                                           [n/kep_hy      [n/ke_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kep_eq      [n/ke_eq]]

    ^{:doc/actions [{}]}                                                                           [n/kerp_1      [n/ker_1]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_2      [n/ker_2]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_3      [n/ker_3]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_4      [n/ker_4]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_5      [n/ker_5]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_6      [n/ker_6]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_7      [n/ker_7]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_8      [n/ker_8]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_9      [n/ker_9]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_0      [n/ker_0]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_hy     [n/ker_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_eq     [n/ker_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/kep_a       [b/ke_a]]
    ^{:doc/actions [{}]}                                                                           [b/kep_b       [b/ke_b]]
    ^{:doc/actions [{}]}                                                                           [b/kep_c       [b/ke_c]]
    ^{:doc/actions [{}]}                                                                           [b/kep_d       [b/ke_d]]
    ^{:doc/actions [{}]}                                                                           [b/kep_e       [b/ke_e]]
    ^{:doc/actions [{}]}                                                                           [b/kep_f       [b/ke_f]]
    ^{:doc/actions [{}]}                                                                           [b/kep_g       [b/ke_g]]
    ^{:doc/actions [{}]}                                                                           [b/kep_h       [b/ke_h]]
    ^{:doc/actions [{}]}                                                                           [b/kep_i       [b/ke_i]]
    ^{:doc/actions [{}]}                                                                           [b/kep_j       [b/ke_j]]
    ^{:doc/actions [{}]}                                                                           [b/kep_k       [b/ke_k]]
    ^{:doc/actions [{}]}                                                                           [b/kep_l       [b/ke_l]]
    ^{:doc/actions [{}]}                                                                           [b/kep_m       [b/ke_m]]
    ^{:doc/actions [{}]}                                                                           [b/kep_n       [b/ke_n]]
    ^{:doc/actions [{}]}                                                                           [b/kep_o       [b/ke_o]]
    ^{:doc/actions [{}]}                                                                           [b/kep_p       [b/ke_p]]
    ^{:doc/actions [{}]}                                                                           [b/kep_q       [b/ke_q]]
    ^{:doc/actions [{}]}                                                                           [b/kep_r       [b/ke_r]]
    ^{:doc/actions [{}]}                                                                           [b/kep_s       [b/ke_s]]
    ^{:doc/actions [{}]}                                                                           [b/kep_t       [b/ke_t]]
    ^{:doc/actions [{}]}                                                                           [b/kep_u       [b/ke_u]]
    ^{:doc/actions [{}]}                                                                           [b/kep_v       [b/ke_v]]
    ^{:doc/actions [{}]}                                                                           [b/kep_w       [b/ke_w]]
    ^{:doc/actions [{}]}                                                                           [b/kep_x       [b/ke_x]]
    ^{:doc/actions [{}]}                                                                           [b/kep_y       [b/ke_y]]
    ^{:doc/actions [{}]}                                                                           [b/kep_z       [b/ke_z]]
    ^{:doc/actions [{}]}                                                                           [b/kep_rt      [b/ke_rt]]

    ^{:doc/actions [{}]}                                                                           [b/kerp_a      [b/ker_a]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_b      [b/ker_b]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_c      [b/ker_c]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_d      [b/ker_d]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_e      [b/ker_e]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_f      [b/ker_f]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_g      [b/ker_g]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_h      [b/ker_h]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_i      [b/ker_i]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_j      [b/ker_j]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_k      [b/ker_k]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_l      [b/ker_l]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_m      [b/ker_m]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_n      [b/ker_n]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_o      [b/ker_o]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_p      [b/ker_p]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_q      [b/ker_q]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_r      [b/ker_r]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_s      [b/ker_s]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_t      [b/ker_t]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_u      [b/ker_u]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_v      [b/ker_v]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_w      [b/ker_w]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_x      [b/ker_x]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_y      [b/ker_y]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_z      [b/ker_z]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_rt     [b/ker_rt]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (ropt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
