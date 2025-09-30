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

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcp_ob    [c/kotc_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_cb    [c/kotc_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_sc    [c/kotc_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_qu    [c/kotc_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_bl    [c/kotc_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_cm    [c/kotc_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_pe    [c/kotc_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_sl    [c/kotc_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj, :action "pane float pin",    :exec zj-toggle-pin}]}            [c/kotcp_db    [c/kos_m]      c/term]
    ^{:doc/actions [{:program c/zj, :action "pane float toggle", :exec zj-toggle-float}]}          [c/kotcp_re    [c/kos_n]      c/term]
    ^{:doc/actions [{:program c/zj, :action "mode search",       :exec zj-entersearch-mode}
                    {:program c/ze, :action "mode normal",       :exec z-normal-mode}]}            [c/kotcp_rs    [c/ko_h]       c/term]
    ^{:doc/actions [{:program c/zj, :action "pane float pop",    :exec zj-toggle-embed}]}          [c/kotcp_ro    [c/kos_l]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kotcp_rc    [c/kotc_rc]]
    ^{:doc/actions [{:program c/zj, :action "mode lock",         :exec zj-locked-mode}
                    {:program c/zl, :action "mode normal",       :exec z-normal-mode}]}            [c/kotcp_sp    [c/ko_g]       c/term]

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
    ^{:doc/actions [{}]}                                                                           [c/kotcp_rt    [c/kotc_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (hyper)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
