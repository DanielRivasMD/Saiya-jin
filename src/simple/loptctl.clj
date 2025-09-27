;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "loptctl.edn")

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

(defn loptctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option - Control Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane left",          :exec zj-pane-left}]}         [c/kotp_al     [c/ko_j]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane right",         :exec zj-pane-right}]}        [c/kotp_ar     [c/ko_k]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane up",            :exec zj-pane-up}]}           [c/kotp_au     [c/ko_l]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane down",          :exec zj-pane-down}]}         [c/kotp_ad     [c/ko_m]       c/term]

    ^{:doc/actions [{}]}                                                                           [c/kotsp_al    [c/kots_al]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_ar    [c/kots_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_au    [c/kots_au]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_ad    [c/kots_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotp_ob     [c/kot_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_cb     [c/kot_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_sc     [c/kot_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_qu     [c/kot_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_bl     [c/kot_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_cm     [c/kot_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_pe     [c/kot_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_sl     [c/kot_sl]]

    ^{:doc/actions [{}]}                                                                           [c/kotsp_ob    [c/kots_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_cb    [c/kots_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_sc    [c/kots_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_qu    [c/kots_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_bl    [c/kots_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_cm    [c/kots_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_pe    [c/kots_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_sl    [c/kots_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane close",         :exec zj-pane-close}]}        [c/kotp_db     [c/ko_o]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane focus",         :exec zj-pane-focus}]}        [c/kotp_re     [c/ko_p]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane split right",   :exec zj-pane-new-right}]}    [c/kotp_rs     [c/ko_q]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane split down",    :exec zj-pane-new-down}]}     [c/kotp_ro     [c/ko_r]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "mode pane rename",   :exec zj-rename-pane-mode}
                    {:program c/zp,    :action "abort pane rename",  :exec zp-abort-rename}]}      [c/kotp_rc     [c/ko_s]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane jump back",     :exec zj-last-pane}]}         [c/kotp_sp     [c/ko_t]       c/term]

    ^{:doc/actions [{}]}                                                                           [c/kotsp_db    [c/kots_db]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_re    [c/kots_re]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_rs    [c/kots_rs]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_ro    [c/kots_ro]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_rc    [c/kots_rc]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_sp    [c/kots_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotp_1      [c/kot_1]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_2      [c/kot_2]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_3      [c/kot_3]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_4      [c/kot_4]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_5      [c/kot_5]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_6      [c/kot_6]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_7      [c/kot_7]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_8      [c/kot_8]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_9      [c/kot_9]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_0      [c/kot_0]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_hy     [c/kot_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_eq     [c/kot_eq]]

    ^{:doc/actions [{}]}                                                                           [c/kotsp_1     [c/kots_1]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_2     [c/kots_2]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_3     [c/kots_3]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_4     [c/kots_4]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_5     [c/kots_5]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_6     [c/kots_6]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_7     [c/kots_7]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_8     [c/kots_8]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_9     [c/kots_9]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_0     [c/kots_0]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_hy    [c/kots_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_eq    [c/kots_eq]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotp_a      [c/kot_a]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_b      [c/kot_b]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_c      [c/kot_c]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_d      [c/kot_d]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_e      [c/kot_e]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_f      [c/kot_f]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_g      [c/kot_g]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_h      [c/kot_h]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_i      [c/kot_i]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_j      [c/kot_j]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_k      [c/kot_k]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_l      [c/kot_l]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_m      [c/kot_m]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_n      [c/kot_n]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_o      [c/kot_o]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_p      [c/kot_p]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_q      [c/kot_q]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_r      [c/kot_r]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_s      [c/kot_s]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_t      [c/kot_t]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_u      [c/kot_u]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_v      [c/kot_v]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_w      [c/kot_w]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_x      [c/kot_x]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_y      [c/kot_y]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_z      [c/kot_z]]
    ^{:doc/actions [{}]}                                                                           [c/kotp_rt     [c/kot_rt]]

    ^{:doc/actions [{}]}                                                                           [c/kotsp_a     [c/kots_a]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_b     [c/kots_b]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_c     [c/kots_c]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_d     [c/kots_d]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_e     [c/kots_e]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_f     [c/kots_f]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_g     [c/kots_g]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_h     [c/kots_h]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_i     [c/kots_i]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_j     [c/kots_j]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_k     [c/kots_k]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_l     [c/kots_l]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_m     [c/kots_m]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_n     [c/kots_n]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_o     [c/kots_o]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_p     [c/kots_p]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_q     [c/kots_q]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_r     [c/kots_r]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_s     [c/kots_s]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_t     [c/kots_t]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_u     [c/kots_u]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_v     [c/kots_v]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_w     [c/kots_w]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_x     [c/kots_x]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_y     [c/kots_y]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_z     [c/kots_z]]
    ^{:doc/actions [{}]}                                                                           [c/kotsp_rt    [c/kots_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
