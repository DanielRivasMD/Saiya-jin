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

    ; technical glyphs
    ^{:doc/actions [{:program c/tm,    :action "R tree update",      :sequence "` %<% `"}]}        [t/kep_ob      [a/k_sp n/ks_5 t/ks_cm n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "R pipe",             :sequence "` %>% `"}]}        [t/kep_cb      [a/k_sp n/ks_5 t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "R assign pipe",      :sequence "` %<>% `"}]}       [t/kep_sc      [a/k_sp n/ks_5 t/ks_cm t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "R tee pipe",         :sequence "` %T>% `"}]}       [t/kep_qu      [a/k_sp n/ks_5 b/ks_t t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "R exposition pipe",  :sequence "` %$% `"}]}        [t/kep_bl      [a/k_sp n/ks_5 n/ks_4 n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "R tidyverse",        :sequence "` %<+% `"}]}       [t/kep_cm      [a/k_sp n/ks_5 t/ks_cm n/ks_eq n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "R tidyverse",        :sequence "` %!>% `"}]}       [t/kep_pe      [a/k_sp n/ks_5 n/ks_1 t/ks_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "R tree update",      :sequence "` %+>% `"}]}       [t/kep_sl      [a/k_sp n/ks_5 n/ks_eq t/ks_pe n/ks_5 a/k_sp]]

    ; action glyphs
    ^{:doc/actions [{:program c/tm     :action "tilde",              :sequence "`~`"}]}            [a/kep_db      [c/ksp_grave]]
    ^{:doc/actions [{:program c/tm,    :action "go assign",          :sequence "` := `"}]}         [a/kep_re      [a/k_sp t/ks_sc n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "define",             :sequence "` => `"}]}         [a/kep_rs      [a/k_sp n/k_eq t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/sys,   :action "prompt espanso"}]}                                 [a/kep_sp      [a/ke_sp]]
    ^{:doc/actions [{:program c/hc,    :action "buffer picker",      :exec hc-buffer-picker}]}     [a/kep_lc      [b/kt_h]       c/term]
    ^{:doc/actions [{:program c/tm,    :action "atuin widget"}
                    {:program c/hc,    :action "file picker",        :exec hc-file-picker}]}       [a/kep_lo      [b/kt_j]       c/term]
    ^{:doc/actions [{:program c/tm,    :action "fzf history"}
                    {:program c/hc,    :action "change picker",      :exec hc-change-picker}]}     [a/kep_lt      [b/kt_o]       c/term]
    ^{:doc/actions [{}]}                                                                           [a/kep_ls      [a/ke_ls]]
    ^{:doc/actions [{}]}                                                                           [a/kep_caps    [a/ke_caps]]
    ^{:doc/actions [{}]}                                                                           [a/kep_tab     [a/ke_tab]]

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
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (ropt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
