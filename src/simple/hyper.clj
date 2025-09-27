;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; HYPER => OTC
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.hyper
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "hyper.edn")

(def zj-swap-tab-left        ["MoveTab \"Left\";"])
(def zj-swap-tab-right       ["MoveTab \"Right\";"])
(def ze-size-inc             ["Resize \"Increase\";"])
(def ze-size-dec             ["Resize \"Decrease\";"])
(def zj-toggle-pin           ["TogglePanePinned;"])
(def zj-toggle-float         ["ToggleFloatingPanes;"])
(def zj-entersearch-mode     ["SwitchToMode \"EnterSearch\"; SearchInput 0;"])
(def zj-toggle-embed         ["TogglePaneEmbedOrFloating;"])
(def zj-locked-mode          ["SwitchToMode \"Locked\";"])
(def z-normal-mode           ["SwitchToMode \"Normal\";"])

(defn hyper []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Hyper Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "page up",            :exec zj-swap-tab-left}]}     [c/kotcp_al    [c/ks_pu]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "page down",          :exec zj-swap-tab-right}]}    [c/kotcp_ar    [c/ks_pd]      c/term]
    ^{:doc/actions [{:program c/ze,    :action "home",               :exec ze-size-inc}]}          [c/kotcp_au    [c/ks_hm]      c/term]
    ^{:doc/actions [{:program c/ze,    :action "end",                :exec ze-size-dec}]}          [c/kotcp_ad    [c/ks_ed]      c/term]

    ^{:doc/actions [{}]}                                                                           [c/kotcsp_al   [c/kotcs_al]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_ar   [c/kotcs_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_au   [c/kotcs_au]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_ad   [c/kotcs_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcp_ob    [c/kotc_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_cb    [c/kotc_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_sc    [c/kotc_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_qu    [c/kotc_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_bl    [c/kotc_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_cm    [c/kotc_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_pe    [c/kotc_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_sl    [c/kotc_sl]]

    ^{:doc/actions [{}]}                                                                           [c/kotcsp_ob   [c/kotcs_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_cb   [c/kotcs_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_sc   [c/kotcs_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_qu   [c/kotcs_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_bl   [c/kotcs_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_cm   [c/kotcs_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_pe   [c/kotcs_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_sl   [c/kotcs_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj, :action "pane float pin",    :exec zj-toggle-pin}]}            [c/kotcp_db    [c/kos_m]      c/term]
    ^{:doc/actions [{:program c/zj, :action "pane float toggle", :exec zj-toggle-float}]}          [c/kotcp_re    [c/kos_n]      c/term]
    ^{:doc/actions [{:program c/zj, :action "mode search",       :exec zj-entersearch-mode}
                    {:program c/ze, :action "mode normal",       :exec z-normal-mode}]}            [c/kotcp_rs    [c/ko_h]       c/term]
    ^{:doc/actions [{:program c/zj, :action "pane float pop",    :exec zj-toggle-embed}]}          [c/kotcp_ro    [c/kos_l]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_rc    [c/kotc_rc]]
    ^{:doc/actions [{:program c/zj, :action "mode lock",         :exec zj-locked-mode}
                    {:program c/zl, :action "mode normal",       :exec z-normal-mode}]}            [c/kotcp_sp    [c/ko_g]       c/term]

    ^{:doc/actions [{}]}                                                                           [c/kotcsp_db   [c/kotcs_db]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_re   [c/kotcs_re]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_rs   [c/kotcs_rs]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_ro   [c/kotcs_ro]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_rc   [c/kotcs_rc]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_sp   [c/kotcs_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcp_1     [c/kotc_1]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_2     [c/kotc_2]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_3     [c/kotc_3]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_4     [c/kotc_4]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_5     [c/kotc_5]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_6     [c/kotc_6]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_7     [c/kotc_7]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_8     [c/kotc_8]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_9     [c/kotc_9]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_0     [c/kotc_0]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_hy    [c/kotc_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_eq    [c/kotc_eq]]

    ^{:doc/actions [{}]}                                                                           [c/kotcsp_1    [c/kotcs_1]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_2    [c/kotcs_2]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_3    [c/kotcs_3]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_4    [c/kotcs_4]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_5    [c/kotcs_5]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_6    [c/kotcs_6]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_7    [c/kotcs_7]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_8    [c/kotcs_8]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_9    [c/kotcs_9]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_0    [c/kotcs_0]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_hy   [c/kotcs_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_eq   [c/kotcs_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcp_a     [c/kotc_a]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_b     [c/kotc_b]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_c     [c/kotc_c]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_d     [c/kotc_d]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_e     [c/kotc_e]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_f     [c/kotc_f]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_g     [c/kotc_g]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_h     [c/kotc_h]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_i     [c/kotc_i]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_j     [c/kotc_j]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_k     [c/kotc_k]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_l     [c/kotc_l]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_m     [c/kotc_m]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_n     [c/kotc_n]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_o     [c/kotc_o]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_p     [c/kotc_p]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_q     [c/kotc_q]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_r     [c/kotc_r]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_s     [c/kotc_s]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_t     [c/kotc_t]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_u     [c/kotc_u]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_v     [c/kotc_v]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_w     [c/kotc_w]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_x     [c/kotc_x]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_y     [c/kotc_y]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_z     [c/kotc_z]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_rt    [c/kotc_rt]]

    ^{:doc/actions [{}]}                                                                           [c/kotcsp_a    [c/kotcs_a]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_b    [c/kotcs_b]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_c    [c/kotcs_c]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_d    [c/kotcs_d]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_e    [c/kotcs_e]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_f    [c/kotcs_f]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_g    [c/kotcs_g]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_h    [c/kotcs_h]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_i    [c/kotcs_i]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_j    [c/kotcs_j]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_k    [c/kotcs_k]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_l    [c/kotcs_l]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_m    [c/kotcs_m]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_n    [c/kotcs_n]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_o    [c/kotcs_o]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_p    [c/kotcs_p]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_q    [c/kotcs_q]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_r    [c/kotcs_r]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_s    [c/kotcs_s]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_t    [c/kotcs_t]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_u    [c/kotcs_u]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_v    [c/kotcs_v]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_w    [c/kotcs_w]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_x    [c/kotcs_x]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_y    [c/kotcs_y]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_z    [c/kotcs_z]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_rt   [c/kotcs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (hyper)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
