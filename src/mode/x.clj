;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Z
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.x
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

(def out-file "x.edn")

(def zj-pane-left            ["MoveFocus \"Left\";"])
(def zj-pane-right           ["MoveFocus \"Right\";"])
(def zj-pane-up              ["MoveFocus \"Up\";"])
(def zj-pane-down            ["MoveFocus \"Down\";"])
(def zj-pane-close           ["CloseFocus;"])
(def zj-pane-focus           ["ToggleFocusFullscreen;"])
(def zj-pane-new-right       ["NewPane \"Right\";"])
(def zj-pane-new-down        ["NewPane \"Down\";"])
(def zj-rename-pane-mode     ["SwitchToMode \"RenamePane\"; PaneNameInput 0;"])
(def zp-abort-rename         ["UndoRenamePane; SwitchToMode \"Normal\";"])
(def zj-last-pane            ["SwitchFocus;"])

(defn x []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "X mode"
   :rules
   [:x-mode
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane left",          :exec zj-pane-left}]}         [r/kp_al       [f/kt_f1]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane right",         :exec zj-pane-right}]}        [r/kp_ar       [f/kt_f2]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane up",            :exec zj-pane-up}]}           [r/kp_au       [f/kt_f4]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane down",          :exec zj-pane-down}]}         [r/kp_ad       [f/kt_f5]       c/term]

    ^{:doc/actions [{}]}                                                                           [r/ksp_al      [r/ks_al]]
    ^{:doc/actions [{}]}                                                                           [r/ksp_ar      [r/ks_ar]]
    ^{:doc/actions [{}]}                                                                           [r/ksp_au      [r/ks_au]]
    ^{:doc/actions [{}]}                                                                           [r/ksp_ad      [r/ks_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kp_ob       [t/k_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kp_cb       [t/k_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sc       [t/k_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kp_qu       [t/k_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kp_bl       [t/k_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kp_cm       [t/k_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kp_pe       [t/k_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sl       [t/k_sl]]

    ^{:doc/actions [{}]}                                                                           [t/ksp_ob      [t/ks_ob]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_cb      [t/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sc      [t/ks_sc]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_qu      [t/ks_qu]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_bl      [t/ks_bl]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_cm      [t/ks_cm]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_pe      [t/ks_pe]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sl      [t/ks_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane close",         :exec zj-pane-close}]}        [a/kp_db       [f/kt_f6]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane focus",         :exec zj-pane-focus}]}        [a/kp_re       [f/kt_f7]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane split right",   :exec zj-pane-new-right}]}    [a/kp_rs       [f/kt_f8]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane split down",    :exec zj-pane-new-down}]}     [a/kp_ro       [f/kt_f9]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "mode pane rename",   :exec zj-rename-pane-mode}
                    {:program c/zp,    :action "abort pane rename",  :exec zp-abort-rename}]}      [a/kp_rc       [f/kt_f10]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane jump back",     :exec zj-last-pane}]}         [a/kp_sp       [f/kt_f11]       c/term]

    ^{:doc/actions [{}]}                                                                           [a/ksp_db      [a/ks_db]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_re      [a/ks_re]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_rs      [a/ks_rs]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_ro      [a/ks_ro]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_rc      [a/ks_rc]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_sp      [a/ks_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/kp_1        [n/k_1]]
    ^{:doc/actions [{}]}                                                                           [n/kp_2        [n/k_2]]
    ^{:doc/actions [{}]}                                                                           [n/kp_3        [n/k_3]]
    ^{:doc/actions [{}]}                                                                           [n/kp_4        [n/k_4]]
    ^{:doc/actions [{}]}                                                                           [n/kp_5        [n/k_5]]
    ^{:doc/actions [{}]}                                                                           [n/kp_6        [n/k_6]]
    ^{:doc/actions [{}]}                                                                           [n/kp_7        [n/k_7]]
    ^{:doc/actions [{}]}                                                                           [n/kp_8        [n/k_8]]
    ^{:doc/actions [{}]}                                                                           [n/kp_9        [n/k_9]]
    ^{:doc/actions [{}]}                                                                           [n/kp_0        [n/k_0]]
    ^{:doc/actions [{}]}                                                                           [n/kp_hy       [n/k_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kp_eq       [n/k_eq]]

    ^{:doc/actions [{}]}                                                                           [n/ksp_1       [n/ks_1]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_2       [n/ks_2]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_3       [n/ks_3]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_4       [n/ks_4]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_5       [n/ks_5]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_6       [n/ks_6]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_7       [n/ks_7]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_8       [n/ks_8]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_9       [n/ks_9]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_0       [n/ks_0]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_hy      [n/ks_hy]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_eq      [n/ks_eq]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [b/kp_a        [b/k_a]]
    ^{:doc/actions [{}]}                                                                           [b/kp_b        [b/k_b]]
    ^{:doc/actions [{}]}                                                                           [b/kp_c        [b/k_c]]
    ^{:doc/actions [{}]}                                                                           [b/kp_d        [b/k_d]]
    ^{:doc/actions [{}]}                                                                           [b/kp_e        [b/k_e]]
    ^{:doc/actions [{}]}                                                                           [b/kp_f        [b/k_f]]
    ^{:doc/actions [{}]}                                                                           [b/kp_g        [b/k_g]]
    ^{:doc/actions [{}]}                                                                           [b/kp_h        [b/k_h]]
    ^{:doc/actions [{}]}                                                                           [b/kp_i        [b/k_i]]
    ^{:doc/actions [{}]}                                                                           [b/kp_j        [b/k_j]]
    ^{:doc/actions [{}]}                                                                           [b/kp_k        [b/k_k]]
    ^{:doc/actions [{}]}                                                                           [b/kp_l        [b/k_l]]
    ^{:doc/actions [{}]}                                                                           [b/kp_m        [b/k_m]]
    ^{:doc/actions [{}]}                                                                           [b/kp_n        [b/k_n]]
    ^{:doc/actions [{}]}                                                                           [b/kp_o        [b/k_o]]
    ^{:doc/actions [{}]}                                                                           [b/kp_p        [b/k_p]]
    ^{:doc/actions [{}]}                                                                           [b/kp_q        [b/k_q]]
    ^{:doc/actions [{}]}                                                                           [b/kp_r        [b/k_r]]
    ^{:doc/actions [{}]}                                                                           [b/kp_s        [b/k_s]]
    ^{:doc/actions [{}]}                                                                           [b/kp_t        [b/k_t]]
    ^{:doc/actions [{}]}                                                                           [b/kp_u        [b/k_u]]
    ^{:doc/actions [{}]}                                                                           [b/kp_v        [b/k_v]]
    ^{:doc/actions [{}]}                                                                           [b/kp_w        [b/k_w]]
    ^{:doc/actions [{}]}                                                                           [b/kp_x        [b/k_x]]
    ^{:doc/actions [{}]}                                                                           [b/kp_y        [b/k_y]]
    ^{:doc/actions [{}]}                                                                           [b/kp_z        [b/k_z]]
    ^{:doc/actions [{}]}                                                                           [b/kp_rt       [b/k_rt]]

    ^{:doc/actions [{}]}                                                                           [b/ksp_a       [b/ks_a]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_b       [b/ks_b]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_c       [b/ks_c]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_d       [b/ks_d]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_e       [b/ks_e]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_f       [b/ks_f]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_g       [b/ks_g]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_h       [b/ks_h]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_i       [b/ks_i]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_j       [b/ks_j]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_k       [b/ks_k]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_l       [b/ks_l]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_m       [b/ks_m]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_n       [b/ks_n]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_o       [b/ks_o]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_p       [b/ks_p]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_q       [b/ks_q]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_r       [b/ks_r]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_s       [b/ks_s]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_t       [b/ks_t]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_u       [b/ks_u]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_v       [b/ks_v]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_w       [b/ks_w]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_x       [b/ks_x]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_y       [b/ks_y]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_z       [b/ks_z]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_rt      [b/ks_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (x)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
