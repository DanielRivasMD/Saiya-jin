;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

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
                    {:program c/mc,    :action "jump split left",    :exec mc-prev-split}]}        [c/ktp_al      [c/kt_al]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump split right",   :exec hc-split-right}
                    {:program c/mc,    :action "jump split right",   :exec mc-next-split}]}        [c/ktp_ar      [c/kt_ar]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump split up",      :exec hc-split-up}
                    {:program c/mc,    :action "jump split up",      :exec mc-prev-split}]}        [c/ktp_au      [c/kt_au]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump split down",    :exec hc-split-down}
                    {:program c/mc,    :action "jump split down",    :exec mc-next-split}]}        [c/ktp_ad      [c/kt_ad]      c/term]

    ^{:doc/actions [{}]}                                                                           [c/ktsp_al     [c/kts_al]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_ar     [c/kts_ar]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_au     [c/kts_au]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_ad     [c/kts_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`[[`"}]}           [c/ktp_ob      [c/k_ob c/k_ob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`]]`"}]}           [c/ktp_cb      [c/k_cb c/k_cb]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_sc      [c/kt_sc]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`'''`"}]}          [c/ktp_qu      [c/k_qu c/k_qu c/k_qu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` || `"}]}         [c/ktp_bl      [c/k_sp c/k_bl c/k_bl c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` << `"}]}         [c/ktp_cm      [c/k_sp c/ks_cm c/ks_cm c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >> `"}]}         [c/ktp_pe      [c/k_sp c/ks_pe c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`/// `"}]}         [c/ktp_sl      [c/k_sl c/k_sl c/k_sl c/k_sp]]

    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`{{`"}]}           [c/ktsp_ob     [c/ks_ob c/ks_ob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`}}`"}]}           [c/ktsp_cb     [c/ks_cb c/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_sc     [c/kts_sc]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`\"\"\"`"}]}       [c/ktsp_qu     [c/ks_qu c/ks_qu c/ks_qu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .|| `"}]}        [c/ktsp_bl     [c/k_sp c/k_pe c/ks_bl c/ks_bl c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` <<< `"}]}        [c/ktsp_cm     [c/k_sp c/ks_cm c/ks_cm c/ks_cm c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >>> `"}]}        [c/ktsp_pe     [c/k_sp c/ks_pe c/ks_pe c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` /// `"}]}        [c/ktsp_sl     [c/k_sl c/k_sl c/k_sl c/k_sp]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [c/ktp_db      [c/kt_db]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` == `"}]}         [c/ktp_re      [c/k_sp c/k_eq c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` => `"}]}         [c/ktp_rs      [c/k_sp c/k_eq c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ** `"}]}         [c/ktp_ro      [c/k_sp c/ks_8 c/ks_8 c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` // `"}]}         [c/ktp_rc      [c/k_sp c/k_sl c/k_sl c/k_sp]]
    ^{:doc/actions [{:program c/hc     :action "jumper"              :exec hc-jump}
                    {:program c/mc     :action "jumper"              :exec mc-jump}]}              [c/ktp_sp      [c/kt_sp]]

    ^{:doc/actions [{}]}                                                                           [c/ktsp_db     [c/kts_db]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .== `"}]}        [c/ktsp_re     [c/k_sp c/k_pe c/k_eq c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .=> `"}]}        [c/ktsp_rs     [c/k_sp c/k_pe c/k_eq c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .** `"}]}        [c/ktsp_ro     [c/k_sp c/k_pe c/ks_8 c/ks_8 c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .// `"}]}        [c/ktsp_rc     [c/k_sp c/k_pe c/k_sl c/k_sl c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_sp     [c/kts_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [c/ktp_1       [c/kt_1]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_2       [c/kt_2]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_3       [c/kt_3]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_4       [c/kt_4]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` %% `"}]}         [c/ktp_5       [c/k_sp c/ks_5 c/ks_5 c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_6       [c/kt_6]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` && `"}]}         [c/ktp_7       [c/k_sp c/ks_7 c/ks_7 c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_8       [c/kt_8]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`((`"}]}           [c/ktp_9       [c/ks_9 c/ks_9]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`))`"}]}           [c/ktp_0       [c/ks_0 c/ks_0]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` -- `"}]}         [c/ktp_hy      [c/k_sp c/k_hy c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ++ `"}]}         [c/ktp_eq      [c/k_sp c/ks_eq c/ks_eq c/k_sp]]

    ^{:doc/actions [{}]}                                                                           [c/ktsp_1      [c/kts_1]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_2      [c/kts_2]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_3      [c/kts_3]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_4      [c/kts_4]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .%% `"}]}        [c/ktsp_5      [c/k_sp c/k_pe c/ks_5 c/ks_5 c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_6      [c/kts_6]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .&& `"}]}        [c/ktsp_7      [c/k_sp c/k_pe c/ks_7 c/ks_7 c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_8      [c/kts_8]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_9      [c/kts_9]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_0      [c/kts_0]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .-- `"}]}        [c/ktsp_hy     [c/k_sp c/k_pe c/k_hy c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .++ `"}]}        [c/ktsp_eq     [c/k_sp c/k_pe c/ks_eq c/ks_eq c/k_sp]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/ktp_a       [c/kt_a]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_b       [c/kt_b]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_c       [c/kt_c]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_d       [c/kt_d]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_e       [c/kt_e]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_f       [c/kt_f]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_g       [c/kt_g]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_h       [c/kt_h]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_i       [c/kt_i]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_j       [c/kt_j]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_k       [c/kt_k]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_l       [c/kt_l]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_m       [c/kt_m]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_n       [c/kt_n]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_o       [c/kt_o]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_p       [c/kt_p]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_q       [c/kt_q]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_r       [c/kt_r]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_s       [c/kt_s]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_t       [c/kt_t]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_u       [c/kt_u]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_v       [c/kt_v]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_w       [c/kt_w]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_x       [c/kt_x]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_y       [c/kt_y]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_z       [c/kt_z]]
    ^{:doc/actions [{}]}                                                                           [c/ktp_rt      [c/kt_rt]]

    ^{:doc/actions [{}]}                                                                           [c/ktsp_a      [c/kts_a]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_b      [c/kts_b]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_c      [c/kts_c]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_d      [c/kts_d]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_e      [c/kts_e]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_f      [c/kts_f]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_g      [c/kts_g]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_h      [c/kts_h]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_i      [c/kts_i]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_j      [c/kts_j]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_k      [c/kts_k]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_l      [c/kts_l]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_m      [c/kts_m]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_n      [c/kts_n]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_o      [c/kts_o]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_p      [c/kts_p]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_q      [c/kts_q]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_r      [c/kts_r]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_s      [c/kts_s]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_t      [c/kts_t]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_u      [c/kts_u]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_v      [c/kts_v]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_w      [c/kts_w]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_x      [c/kts_x]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_y      [c/kts_y]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_z      [c/kts_z]]
    ^{:doc/actions [{}]}                                                                           [c/ktsp_rt     [c/kts_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
