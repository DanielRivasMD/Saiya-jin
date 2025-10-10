;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
						))

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
    ^{:doc/actions [{:program c/zj,    :action "pane left",          :exec zj-pane-left}]}         [r/kotp_al     [b/ko_j]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane right",         :exec zj-pane-right}]}        [r/kotp_ar     [b/ko_k]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane up",            :exec zj-pane-up}]}           [r/kotp_au     [b/ko_l]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane down",          :exec zj-pane-down}]}         [r/kotp_ad     [b/ko_m]       c/term]

    ^{:doc/actions [{}]}                                                                           [r/kotsp_al    [r/kots_al]]
    ^{:doc/actions [{}]}                                                                           [r/kotsp_ar    [r/kots_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kotsp_au    [r/kots_au]]
    ^{:doc/actions [{}]}                                                                           [r/kotsp_ad    [r/kots_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kotp_ob     [t/kot_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kotp_cb     [t/kot_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kotp_sc     [t/kot_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kotp_qu     [t/kot_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kotp_bl     [t/kot_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kotp_cm     [t/kot_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kotp_pe     [t/kot_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kotp_sl     [t/kot_sl]]

    ^{:doc/actions [{}]}                                                                           [t/kotsp_ob    [t/kots_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kotsp_cb    [t/kots_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kotsp_sc    [t/kots_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kotsp_qu    [t/kots_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kotsp_bl    [t/kots_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kotsp_cm    [t/kots_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kotsp_pe    [t/kots_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kotsp_sl    [t/kots_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane close",         :exec zj-pane-close}]}        [a/kotp_db     [b/ko_o]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane focus",         :exec zj-pane-focus}]}        [a/kotp_re     [b/ko_p]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane split right",   :exec zj-pane-new-right}]}    [a/kotp_rs     [b/ko_q]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane split down",    :exec zj-pane-new-down}]}     [a/kotp_ro     [b/ko_r]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "mode pane rename",   :exec zj-rename-pane-mode}
                    {:program c/zp,    :action "abort pane rename",  :exec zp-abort-rename}]}      [a/kotp_rc     [b/ko_s]       c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane jump back",     :exec zj-last-pane}]}         [a/kotp_sp     [b/ko_t]       c/term]

    ^{:doc/actions [{}]}                                                                           [a/kotsp_db    [a/kots_db]]
    ^{:doc/actions [{}]}                                                                           [a/kotsp_re    [a/kots_re]]
    ^{:doc/actions [{}]}                                                                           [a/kotsp_rs    [a/kots_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kotsp_ro    [a/kots_ro]]
    ^{:doc/actions [{}]}                                                                           [a/kotsp_rc    [a/kots_rc]]
    ^{:doc/actions [{}]}                                                                           [a/kotsp_sp    [a/kots_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/kotp_1      [n/kot_1]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_2      [n/kot_2]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_3      [n/kot_3]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_4      [n/kot_4]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_5      [n/kot_5]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_6      [n/kot_6]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_7      [n/kot_7]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_8      [n/kot_8]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_9      [n/kot_9]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_0      [n/kot_0]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_hy     [n/kot_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kotp_eq     [n/kot_eq]]

    ^{:doc/actions [{}]}                                                                           [n/kotsp_1     [n/kots_1]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_2     [n/kots_2]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_3     [n/kots_3]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_4     [n/kots_4]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_5     [n/kots_5]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_6     [n/kots_6]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_7     [n/kots_7]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_8     [n/kots_8]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_9     [n/kots_9]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_0     [n/kots_0]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_hy    [n/kots_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kotsp_eq    [n/kots_eq]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [b/kotp_a      [b/kot_a]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_b      [b/kot_b]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_c      [b/kot_c]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_d      [b/kot_d]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_e      [b/kot_e]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_f      [b/kot_f]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_g      [b/kot_g]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_h      [b/kot_h]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_i      [b/kot_i]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_j      [b/kot_j]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_k      [b/kot_k]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_l      [b/kot_l]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_m      [b/kot_m]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_n      [b/kot_n]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_o      [b/kot_o]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_p      [b/kot_p]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_q      [b/kot_q]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_r      [b/kot_r]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_s      [b/kot_s]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_t      [b/kot_t]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_u      [b/kot_u]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_v      [b/kot_v]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_w      [b/kot_w]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_x      [b/kot_x]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_y      [b/kot_y]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_z      [b/kot_z]]
    ^{:doc/actions [{}]}                                                                           [b/kotp_rt     [b/kot_rt]]

    ^{:doc/actions [{}]}                                                                           [b/kotsp_a     [b/kots_a]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_b     [b/kots_b]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_c     [b/kots_c]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_d     [b/kots_d]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_e     [b/kots_e]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_f     [b/kots_f]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_g     [b/kots_g]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_h     [b/kots_h]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_i     [b/kots_i]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_j     [b/kots_j]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_k     [b/kots_k]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_l     [b/kots_l]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_m     [b/kots_m]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_n     [b/kots_n]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_o     [b/kots_o]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_p     [b/kots_p]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_q     [b/kots_q]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_r     [b/kots_r]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_s     [b/kots_s]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_t     [b/kots_t]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_u     [b/kots_u]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_v     [b/kots_v]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_w     [b/kots_w]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_x     [b/kots_x]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_y     [b/kots_y]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_z     [b/kots_z]]
    ^{:doc/actions [{}]}                                                                           [b/kotsp_rt    [b/kots_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
