;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "loptcmd.edn")

(def zj-plug-jump ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/zellij-jump-list.wasm\" { floating true; move_to_focused_tab true; };"])
(def zj-plug-monocle ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/monocle.wasm\" { floating true; }; SwitchToMode \"Normal\";"])
(def zj-plug-room ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/room.wasm\" { floating true; ignore_case true; };"])

(def zj-prev-tab ["GoToPreviousTab;"])
(def zj-next-tab ["GoToNextTab;"])
(def hc-scroll-up ["scroll_up"])
(def lg-scroll-up ["scrollUpMain-alt2"])
(def hc-scroll-down ["scroll_down"])
(def lg-scroll-down ["scrollDownMain-alt2"])
(def zj-toggle-sync ["ToggleActiveSyncTab;"])
(def nu [])
(def zj-close-tab ["CloseTab;"])
(def zj-new-tab ["NewTab;"])
(def zj-break-pane ["BreakPane;"])
(def zj-rename-tab-mode ["SwitchToMode \"RenameTab\"; TabNameInput 0;"])
(def zt-abort-rename ["UndoRenameTab; SwitchToMode \"Normal\";"])
(def zj-last-tab ["ToggleTab;"])
(def zj-tab-1 ["SwitchToMode \"Tab\"; GoToTab 1; SwitchToMode \"Normal\";"])
(def zj-tab-2 ["SwitchToMode \"Tab\"; GoToTab 2; SwitchToMode \"Normal\";"])
(def zj-tab-3 ["SwitchToMode \"Tab\"; GoToTab 3; SwitchToMode \"Normal\";"])
(def zj-tab-4 ["SwitchToMode \"Tab\"; GoToTab 4; SwitchToMode \"Normal\";"])
(def zj-tab-5 ["SwitchToMode \"Tab\"; GoToTab 5; SwitchToMode \"Normal\";"])
(def zj-tab-6 ["SwitchToMode \"Tab\"; GoToTab 6; SwitchToMode \"Normal\";"])
(def zj-tab-7 ["SwitchToMode \"Tab\"; GoToTab 7; SwitchToMode \"Normal\";"])
(def zj-tab-8 ["SwitchToMode \"Tab\"; GoToTab 8; SwitchToMode \"Normal\";"])
(def zj-tab-9 ["SwitchToMode \"Tab\"; GoToTab 9; SwitchToMode \"Normal\";"])

(defn loptcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option - Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "jump prev buffer",   :exec zj-prev-tab}]}          [(c/mk c/bocp c/al) [:!SOj]   :term]
    ^{:doc/actions [{:program c/zj,    :action "jump next buffer",   :exec zj-next-tab}]}          [(c/mk c/bocp c/ar) [:!SOk]   :term]
    ^{:doc/actions [{:program c/hc,    :action "scroll up",          :exec hc-scroll-up}
                    {:program c/lg,    :action "scroll up",          :exec lg-scroll-up}]}         [(c/mk c/bocp c/au) [:!Tx]    :term]
    ^{:doc/actions [{:program c/hc,    :action "scroll down",        :exec hc-scroll-down}
                    {:program c/lg,    :action "scroll down",        :exec lg-scroll-down}]}       [(c/mk c/bocp c/ad) [:!Ty]    :term]

    ^{:doc/actions [{}]} [(c/mk c/bocsp c/al)    [(c/mk c/bocs c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/ar)    [(c/mk c/bocs c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/au)    [(c/mk c/bocs c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/ad)    [(c/mk c/bocs c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{:program c/zj,    :action "tab sync",           :exec zj-toggle-sync}]}       [(c/mk c/bocp c/ob) [:!SOv]   :term]
    ^{:doc/actions [{:program c/zj,    :action "plugin jump-list",   :exec zj-plug-jump}]}         [(c/mk c/bocp c/cb) [:!SOw]   :term]
    ^{:doc/actions [{:program c/zj,    :action "plugin monocle",     :exec zj-plug-monocle}]}      [(c/mk c/bocp c/sc) [:!SOx]   :term]
    ^{:doc/actions [{}]} [(c/mk c/bocp c/qu)     [:!SOy] :term]
    ^{:doc/actions [{}]} [(c/mk c/bocp c/bl)     [:!SOz] :term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [(c/mk c/bocp c/pe) [:!Ox]    :term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [(c/mk c/bocp c/cm) [:!Oy]    :term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [(c/mk c/bocp c/sl) [:!Oz]    :term]

    ^{:doc/actions [{}]} [(c/mk c/bocsp c/ob)    [(c/mk c/bocs c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/cb)    [(c/mk c/bocs c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/sc)    [(c/mk c/bocs c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/qu)    [(c/mk c/bocs c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/bl)    [(c/mk c/bocs c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/cm)    [(c/mk c/bocs c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/pe)    [(c/mk c/bocs c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/sl)    [(c/mk c/bocs c/sl)]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "tab close",          :exec zj-close-tab}]}         [(c/mk c/bocp c/db) [:!SOu]   :term]
    ^{:doc/actions [{:program c/zj,    :action "plugin room",        :exec zj-plug-room}]}         [(c/mk c/bocp c/re) [:!SOp]   :term]
    ^{:doc/actions [{:program c/zj,    :action "tab new",            :exec zj-new-tab}]}           [(c/mk c/bocp c/rs) [:!SOq]   :term]
    ^{:doc/actions [{:program c/zj,    :action "tab break pane",     :exec zj-break-pane}]}        [(c/mk c/bocp c/ro) [:!SOr]   :term]
    ^{:doc/actions [{:program c/zj,    :action "mode tab rename",    :exec zj-rename-tab-mode}
                    {:program c/zt,    :action "abort tab reanme",   :exec zt-abort-rename}]}      [(c/mk c/bocp c/rc) [:!SOs]   :term]
    ^{:doc/actions [{:program c/zj,    :action "tab jump back",      :exec zj-last-tab}]}          [(c/mk c/bocp c/sp) [:!SOt]   :term]

    ^{:doc/actions [{}]} [(c/mk c/bocsp c/db)    [(c/mk c/bocs c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/re)    [(c/mk c/bocs c/re)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/rs)    [(c/mk c/bocs c/rs)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/ro)    [(c/mk c/bocs c/ro)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/rc)    [(c/mk c/bocs c/rc)]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/sp)    [(c/mk c/bocs c/sp)]]

    ; numeric glyphs
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-1",          :exec zj-tab-1}]}             [(c/mk c/botcp "1") [:!SOa]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-2",          :exec zj-tab-2}]}             [(c/mk c/botcp "2") [:!SOb]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-3",          :exec zj-tab-3}]}             [(c/mk c/botcp "3") [:!SOc]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-4",          :exec zj-tab-4}]}             [(c/mk c/botcp "4") [:!SOd]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-5",          :exec zj-tab-5}]}             [(c/mk c/botcp "5") [:!SOe]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-6",          :exec zj-tab-6}]}             [(c/mk c/botcp "6") [:!SOf]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-7",          :exec zj-tab-7}]}             [(c/mk c/botcp "7") [:!SOg]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-8",          :exec zj-tab-8}]}             [(c/mk c/botcp "8") [:!SOh]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-9",          :exec zj-tab-9}]}             [(c/mk c/botcp "9") [:!SOi]   :term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-0",          :exec zj-tab-9}]}             [(c/mk c/botcp "0") [:!SOj]   :term]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/hy)    [(c/mk c/botc "0")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/eq)    [(c/mk c/botc c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/eq)    [(c/mk c/botc c/eq)]]

    ^{:doc/acions [{}]} [(c/mk c/bocsp "1")      [(c/mk c/bocs "1")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "2")      [(c/mk c/bocs "2")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "3")      [(c/mk c/bocs "3")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "4")      [(c/mk c/bocs "4")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "5")      [(c/mk c/bocs "5")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "6")      [(c/mk c/bocs "6")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "7")      [(c/mk c/bocs "7")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "8")      [(c/mk c/bocs "8")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "9")      [(c/mk c/bocs "9")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp "0")      [(c/mk c/bocs "0")]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp c/hy)     [(c/mk c/bocs c/hy)]]
    ^{:doc/acions [{}]} [(c/mk c/bocsp c/eq)     [(c/mk c/bocs c/eq)]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]} [(c/mk c/bocp "a")      [(c/mk c/boc "a")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "b")      [(c/mk c/boc "b")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "c")      [(c/mk c/boc "c")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "d")      [(c/mk c/boc "d")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "e")      [(c/mk c/boc "e")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "f")      [(c/mk c/boc "f")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "g")      [(c/mk c/boc "g")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "h")      [(c/mk c/boc "h")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "i")      [(c/mk c/boc "i")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "j")      [(c/mk c/boc "j")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "k")      [(c/mk c/boc "k")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "l")      [(c/mk c/boc "l")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "m")      [(c/mk c/boc "m")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "n")      [(c/mk c/boc "n")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "o")      [(c/mk c/boc "o")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "p")      [(c/mk c/boc "p")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "q")      [(c/mk c/boc "q")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "r")      [(c/mk c/boc "r")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "s")      [(c/mk c/boc "s")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "t")      [(c/mk c/boc "t")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "u")      [(c/mk c/boc "u")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "v")      [(c/mk c/boc "v")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "w")      [(c/mk c/boc "w")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "x")      [(c/mk c/boc "x")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "y")      [(c/mk c/boc "y")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp "z")      [(c/mk c/boc "z")]]
    ^{:doc/actions [{}]} [(c/mk c/bocp c/rt)     [(c/mk c/boc c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/bocsp "a")     [(c/mk c/bocs "a")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "b")     [(c/mk c/bocs "b")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "c")     [(c/mk c/bocs "c")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "d")     [(c/mk c/bocs "d")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "e")     [(c/mk c/bocs "e")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "f")     [(c/mk c/bocs "f")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "g")     [(c/mk c/bocs "g")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "h")     [(c/mk c/bocs "h")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "i")     [(c/mk c/bocs "i")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "j")     [(c/mk c/bocs "j")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "k")     [(c/mk c/bocs "k")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "l")     [(c/mk c/bocs "l")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "m")     [(c/mk c/bocs "m")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "n")     [(c/mk c/bocs "n")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "o")     [(c/mk c/bocs "o")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "p")     [(c/mk c/bocs "p")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "q")     [(c/mk c/bocs "q")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "r")     [(c/mk c/bocs "r")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "s")     [(c/mk c/bocs "s")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "t")     [(c/mk c/bocs "t")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "u")     [(c/mk c/bocs "u")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "v")     [(c/mk c/bocs "v")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "w")     [(c/mk c/bocs "w")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "x")     [(c/mk c/bocs "x")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "y")     [(c/mk c/bocs "y")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp "z")     [(c/mk c/bocs "z")]]
    ^{:doc/actions [{}]} [(c/mk c/bocsp c/rt)    [(c/mk c/bocs c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
