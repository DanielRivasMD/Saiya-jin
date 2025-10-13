;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
						))

(def out-file "lctl.edn")

(def hc-split-left           ["jump_view_left"])
(def mc-prev-split           ["PreviousSplit"])
(def hc-split-right          ["jump_view_right"])
(def mc-next-split           ["NextSplit"])
(def hc-split-up             ["jump_view_up"])
(def hc-split-down           ["jump_view_down"])
(def hc-jump                 ["goto_word"])
(def mc-jump                 ["JumpLine"])

(defn lctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump split left",    :exec hc-split-left}
                    {:program c/mc,    :action "jump split left",    :exec mc-prev-split}]}        [r/ktp_al      [r/kt_al]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump split right",   :exec hc-split-right}
                    {:program c/mc,    :action "jump split right",   :exec mc-next-split}]}        [r/ktp_ar      [r/kt_ar]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump split up",      :exec hc-split-up}
                    {:program c/mc,    :action "jump split up",      :exec mc-prev-split}]}        [r/ktp_au      [r/kt_au]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump split down",    :exec hc-split-down}
                    {:program c/mc,    :action "jump split down",    :exec mc-next-split}]}        [r/ktp_ad      [r/kt_ad]      c/term]

    ^{:doc/actions [{}]}                                                                           [r/ktsp_al     [r/kts_al]]
    ^{:doc/actions [{}]}                                                                           [r/ktsp_ar     [r/kts_ar]]
    ^{:doc/actions [{}]}                                                                           [r/ktsp_au     [r/kts_au]]
    ^{:doc/actions [{}]}                                                                           [r/ktsp_ad     [r/kts_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`[[`"}]}           [t/ktp_ob      [t/k_ob t/k_ob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`]]`"}]}           [t/ktp_cb      [t/k_cb t/k_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ktp_sc      [t/kt_sc]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`'''`"}]}          [t/ktp_qu      [t/k_qu t/k_qu t/k_qu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` || `"}]}         [t/ktp_bl      [a/k_sp t/k_bl t/k_bl a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` << `"}]}         [t/ktp_cm      [a/k_sp t/ks_cm t/ks_cm a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >> `"}]}         [t/ktp_pe      [a/k_sp t/ks_pe t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`/// `"}]}         [t/ktp_sl      [t/k_sl t/k_sl t/k_sl a/k_sp]]

    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`{{`"}]}           [t/ktsp_ob     [t/ks_ob t/ks_ob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`}}`"}]}           [t/ktsp_cb     [t/ks_cb t/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ktsp_sc     [t/kts_sc]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`\"\"\"`"}]}       [t/ktsp_qu     [t/ks_qu t/ks_qu t/ks_qu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .|| `"}]}        [t/ktsp_bl     [a/k_sp t/k_pe t/ks_bl t/ks_bl a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` <<< `"}]}        [t/ktsp_cm     [a/k_sp t/ks_cm t/ks_cm t/ks_cm a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >>> `"}]}        [t/ktsp_pe     [a/k_sp t/ks_pe t/ks_pe t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` /// `"}]}        [t/ktsp_sl     [t/k_sl t/k_sl t/k_sl a/k_sp]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/ktp_db      [a/kt_db]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` == `"}]}         [a/ktp_re      [a/k_sp n/k_eq n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` => `"}]}         [a/ktp_rs      [a/k_sp n/k_eq t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ** `"}]}         [a/ktp_ro      [a/k_sp n/ks_8 n/ks_8 a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` // `"}]}         [a/ktp_rc      [a/k_sp t/k_sl t/k_sl a/k_sp]]
    ^{:doc/actions [{:program c/hc     :action "jumper"              :exec hc-jump}
                    {:program c/mc     :action "jumper"              :exec mc-jump}]}              [a/ktp_sp      [a/kt_sp]]

    ^{:doc/actions [{}]}                                                                           [a/ktsp_db     [a/kts_db]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .== `"}]}        [a/ktsp_re     [a/k_sp t/k_pe n/k_eq n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .=> `"}]}        [a/ktsp_rs     [a/k_sp t/k_pe n/k_eq t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .** `"}]}        [a/ktsp_ro     [a/k_sp t/k_pe n/ks_8 n/ks_8 a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .// `"}]}        [a/ktsp_rc     [a/k_sp t/k_pe t/k_sl t/k_sl a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [a/ktsp_sp     [a/kts_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/ktp_1       [n/kt_1]]
    ^{:doc/actions [{}]}                                                                           [n/ktp_2       [n/kt_2]]
    ^{:doc/actions [{}]}                                                                           [n/ktp_3       [n/kt_3]]
    ^{:doc/actions [{}]}                                                                           [n/ktp_4       [n/kt_4]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` %% `"}]}         [n/ktp_5       [a/k_sp n/ks_5 n/ks_5 a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [n/ktp_6       [n/kt_6]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` && `"}]}         [n/ktp_7       [a/k_sp n/ks_7 n/ks_7 a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [n/ktp_8       [n/kt_8]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`((`"}]}           [n/ktp_9       [n/ks_9 n/ks_9]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`))`"}]}           [n/ktp_0       [n/ks_0 n/ks_0]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` -- `"}]}         [n/ktp_hy      [a/k_sp n/k_hy n/k_hy a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ++ `"}]}         [n/ktp_eq      [a/k_sp n/ks_eq n/ks_eq a/k_sp]]

    ^{:doc/actions [{}]}                                                                           [n/ktsp_1      [n/kts_1]]
    ^{:doc/actions [{}]}                                                                           [n/ktsp_2      [n/kts_2]]
    ^{:doc/actions [{}]}                                                                           [n/ktsp_3      [n/kts_3]]
    ^{:doc/actions [{}]}                                                                           [n/ktsp_4      [n/kts_4]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .%% `"}]}        [n/ktsp_5      [a/k_sp t/k_pe n/ks_5 n/ks_5 a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [n/ktsp_6      [n/kts_6]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .&& `"}]}        [n/ktsp_7      [a/k_sp t/k_pe n/ks_7 n/ks_7 a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [n/ktsp_8      [n/kts_8]]
    ^{:doc/actions [{}]}                                                                           [n/ktsp_9      [n/kts_9]]
    ^{:doc/actions [{}]}                                                                           [n/ktsp_0      [n/kts_0]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .-- `"}]}        [n/ktsp_hy     [a/k_sp t/k_pe n/k_hy n/k_hy a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .++ `"}]}        [n/ktsp_eq     [a/k_sp t/k_pe n/ks_eq n/ks_eq a/k_sp]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/ktp_a       [b/kt_a]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_b       [b/kt_b]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_c       [b/kt_c]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_d       [b/kt_d]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_e       [b/kt_e]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_f       [b/kt_f]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_g       [b/kt_g]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_h       [b/kt_h]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_i       [b/kt_i]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_j       [b/kt_j]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_k       [b/kt_k]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_l       [b/kt_l]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_m       [b/kt_m]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_n       [b/kt_n]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_o       [b/kt_o]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_p       [b/kt_p]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_q       [b/kt_q]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_r       [b/kt_r]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_s       [b/kt_s]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_t       [b/kt_t]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_u       [b/kt_u]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_v       [b/kt_v]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_w       [b/kt_w]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_x       [b/kt_x]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_y       [b/kt_y]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_z       [b/kt_z]]
    ^{:doc/actions [{}]}                                                                           [b/ktp_rt      [b/kt_rt]]

    ^{:doc/actions [{}]}                                                                           [b/ktsp_a      [b/kts_a]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_b      [b/kts_b]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_c      [b/kts_c]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_d      [b/kts_d]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_e      [b/kts_e]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_f      [b/kts_f]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_g      [b/kts_g]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_h      [b/kts_h]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_i      [b/kts_i]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_j      [b/kts_j]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_k      [b/kts_k]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_l      [b/kts_l]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_m      [b/kts_m]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_n      [b/kts_n]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_o      [b/kts_o]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_p      [b/kts_p]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_q      [b/kts_q]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_r      [b/kts_r]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_s      [b/kts_s]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_t      [b/kts_t]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_u      [b/kts_u]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_v      [b/kts_v]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_w      [b/kts_w]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_x      [b/kts_x]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_y      [b/kts_y]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_z      [b/kts_z]]
    ^{:doc/actions [{}]}                                                                           [b/ktsp_rt     [b/kts_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
