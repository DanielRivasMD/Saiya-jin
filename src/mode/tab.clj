;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; TAB
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.tab
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

(def out-file "tab.edn")

(def zj-plug-jump            ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/zellij-jump-list.wasm\" { floating true; move_to_focused_tab true; };"])
(def zj-plug-monocle         ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/monocle.wasm\" { floating true; }; SwitchToMode \"Normal\";"])
(def zj-plug-room            ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/room.wasm\" { floating true; ignore_case true; };"])

(def zj-prev-tab             ["GoToPreviousTab;"])
(def zj-next-tab             ["GoToNextTab;"])
(def hc-scroll-up            ["scroll_up"])
(def lg-scroll-up            ["scrollUpMain-alt2"])
(def hc-scroll-down          ["scroll_down"])
(def lg-scroll-down          ["scrollDownMain-alt2"])
(def zj-swap-tab-left        ["MoveTab \"Left\";"])
(def zj-swap-tab-right       ["MoveTab \"Right\";"])
(def zj-toggle-sync          ["ToggleActiveSyncTab;"])
(def zj-close-tab            ["CloseTab;"])
(def zj-new-tab              ["NewTab;"])
(def zj-break-pane           ["BreakPane;"])
(def zj-rename-tab-mode      ["SwitchToMode \"RenameTab\"; TabNameInput 0;"])
(def zt-abort-rename         ["UndoRenameTab; SwitchToMode \"Normal\";"])
(def zj-last-tab             ["ToggleTab;"])

(defn tab-mode []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Tab Mode"
   :rules
   [:tab-mode
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "prev tab",           :exec zj-prev-tab}]}          [r/kp_al       [f/ks_f1]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "next tab",           :exec zj-next-tab}]}          [r/kp_ar       [f/ks_f2]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "scroll up",          :exec hc-scroll-up}
                    {:program c/lg,    :action "scroll up",          :exec lg-scroll-up}]}         [r/kp_au       [b/kt_x]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "scroll down",        :exec hc-scroll-down}
                    {:program c/lg,    :action "scroll down",        :exec lg-scroll-down}]}       [r/kp_ad       [b/kt_y]       c/term]

   ; technical glyphs
    ^{:doc/actions [{:program c/zj,    :action "page up",            :exec zj-swap-tab-left}]}     [t/kp_ob       [f/kts_f1]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "page down",          :exec zj-swap-tab-right}]}    [t/kp_cb       [f/kts_f2]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin monocle",     :exec zj-plug-monocle}]}      [t/kp_sc       [f/ks_f6]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin jump-list",   :exec zj-plug-jump}]}         [t/kp_qu       [f/ks_f5]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab sync",           :exec zj-toggle-sync}]}       [t/kp_bl       [f/ks_f4]      c/term]
    ; TODO: relocate z-mode, update empty
    ; ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/kp_pe       [b/ko_x]       c/term]
    ; ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/kp_cm       [b/ko_y]       c/term]
    ; ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/kp_sl       [b/ko_z]       c/term]

   ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "tab close",          :exec zj-close-tab}]}         [a/kp_db       [f/ks_f7]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin room",        :exec zj-plug-room}]}         [a/kp_re       [f/ks_f8]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab new",            :exec zj-new-tab}]}           [a/kp_rs       [f/ks_f9]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab break pane",     :exec zj-break-pane}]}        [a/kp_ro       [f/ks_f10]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "mode tab rename",    :exec zj-rename-tab-mode}
                    {:program c/zt,    :action "abort tab reanme",   :exec zt-abort-rename}]}      [a/kp_rc       [f/ks_f11]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab jump back",      :exec zj-last-tab}]}          [a/kp_sp       [f/ks_f12]     c/term]

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
		]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (tab-mode)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
