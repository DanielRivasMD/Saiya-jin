;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "loptcmd.edn")

(defn loptcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option - Command Mode"
   :rules
   [;
  ; arrow glyphs
    ^{:doc/actions [{:program "zellij", :action "jump prev buffer", :exec ["GoToPreviousTab;"]}]}
    [:!OC#Pleft_arrow          [:!SOj]             :term]

    ^{:doc/actions [{:program "zellij", :action "jump next buffer", :exec ["GoToNextTab;"]}]}
    [:!OC#Pright_arrow         [:!SOk]             :term]

    ^{:doc/actions [{:action "scroll up", :exec ["scroll_up"], :program "helix-common"}
                    {:action "scroll up", :exec ["scrollUpMain-alt2"], :program "lazygit"}]}
    [:!OC#Pup_arrow            [:!Tx]              :term]

    ^{:doc/actions [{:action "scroll down", :exec ["scroll_down"], :program "helix-common"}
                    {:action "scroll down", :exec ["scrollDownMain-alt2"], :program "lazygit"}]}
    [:!OC#Pdown_arrow          [:!Ty]              :term]

    ^{:doc/actions [{}]} [:!OCS#Pleft_arrow  [:!OCSleft_arrow]  [:term]]
    ^{:doc/actions [{}]} [:!OCS#Pright_arrow [:!OCSright_arrow] [:term]]
    ^{:doc/actions [{}]} [:!OCS#Pup_arrow    [:!OCSup_arrow]    [:term]]
    ^{:doc/actions [{}]} [:!OCS#Pdown_arrow  [:!OCSdown_arrow]  [:term]]

  ; technical glyphs
    ^{:doc/actions [{:action "tab sync",  :exec ["ToggleActiveSyncTab;"], :program "zellij"}]}    [:!OC#Popen_bracket  [:!SOv] [:term]]
    ^{:doc/actions [{:action "plugin jump-list",  :exec ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/zellij-jump-list.wasm\" { floating true; move_to_focused_tab true; };"], :program "zellij"}]}    [:!OC#Pclose_bracket [:!SOw] [:term]]
    ^{:doc/actions [{:action "plugin monocle",   :exec ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/monocle.wasm\" { floating true; }; SwitchToMode \"Normal\";"], :program "zellij"}]}    [:!OC#Psemicolon     [:!SOx] [:term]]
    ^{:doc/actions [{}]}    [:!OC#Pquote         [:!SOy] [:term]]
    ^{:doc/actions [{}]}    [:!OC#Pbackslash     [:!SOz] [:term]]
    ^{:doc/actions [{:action "nushell motion",   :exec [], :program "alacritty"}]} [:!OC#Pcomma         [:!Ox]  [:term]]
    ^{:doc/actions [{:action "nushell motion",   :exec [], :program "alacritty"}]} [:!OC#Pperiod        [:!Oy]  [:term]]
    ^{:doc/actions [{:action "nushell motion",   :exec [], :program "alacritty"}]} [:!OC#Pslash         [:!Oz]  [:term]]

    ^{:doc/actions [{}]} [:!OCS#Popen_bracket   [:!OCSopen_bracket]]
    ^{:doc/actions [{}]} [:!OCS#Pclose_bracket  [:!OCSclose_bracket]]
    ^{:doc/actions [{}]} [:!OCS#Psemicolon      [:!OCSsemicolon]]
    ^{:doc/actions [{}]} [:!OCS#Pquote          [:!OCSquote]]
    ^{:doc/actions [{}]} [:!OCS#Pbackslash      [:!OCSbackslash]]
    ^{:doc/actions [{}]} [:!OCS#Pcomma          [:!OCScomma]]
    ^{:doc/actions [{}]} [:!OCS#Pperiod         [:!OCSperiod]]
    ^{:doc/actions [{}]} [:!OCS#Pslash          [:!OCSslash]]

  ; action glyphs
    ^{:doc/actions [{:action "tab close",     :exec ["CloseTab;"], :program "zellij"}]} [:!OC#Pdelete_or_backspace [:!SOu] [:term]]
    ^{:doc/actions [{:action "plugin room",      :exec ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/room.wasm\" { floating true; ignore_case true; };"], :program "zellij"}]} [:!OC#Preturn_or_enter     [:!SOp] [:term]]
    ^{:doc/actions [{:action "tab new",       :exec ["NewTab;"], :program "zellij"}]} [:!OC#Pright_shift         [:!SOq] [:term]]
    ^{:doc/actions [{:action "tab break pane",:exec ["BreakPane;"], :program "zellij"}]} [:!OC#Pright_option        [:!SOr] [:term]]
    ^{:doc/actions [{:action "tab rename",    :exec ["SwitchToMode \"RenameTab\"; TabNameInput 0;"], :program "zellij"}]} [:!OC#Pright_command       [:!SOs] [:term]]
    ^{:doc/actions [{:action "tab jump back", :exec ["ToggleTab;"], :program "zellij"}]} [:!OC#Pspacebar            [:!SOt] [:term]]

    ^{:doc/actions [{}]} [:!OCS#Pdelete_or_backspace [:!OCSdelete_or_backspace]]
    ^{:doc/actions [{}]} [:!OCS#Preturn_or_enter     [:!OCSreturn_or_enter]]
    ^{:doc/actions [{}]} [:!OCS#Pright_shift         [:!OCSright_shift]]
    ^{:doc/actions [{}]} [:!OCS#Pright_option        [:!OCSright_option]]
    ^{:doc/actions [{}]} [:!OCS#Pright_command       [:!OCSright_command]]
    ^{:doc/actions [{}]} [:!OCS#Pspacebar            [:!OCSspacebar]]

  ; numeric glyphs
    ^{:doc/actions [{:action "alt-cmd-1", :exec ["SwitchToMode \"Tab\"; GoToTab 1; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P1 [:!SOa] [:term]]
    ^{:doc/actions [{:action "alt-cmd-2", :exec ["SwitchToMode \"Tab\"; GoToTab 2; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P2 [:!SOb] [:term]]
    ^{:doc/actions [{:action "alt-cmd-3", :exec ["SwitchToMode \"Tab\"; GoToTab 3; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P3 [:!SOc] [:term]]
    ^{:doc/actions [{:action "alt-cmd-4", :exec ["SwitchToMode \"Tab\"; GoToTab 4; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P4 [:!SOd] [:term]]
    ^{:doc/actions [{:action "alt-cmd-5", :exec ["SwitchToMode \"Tab\"; GoToTab 5; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P5 [:!SOe] [:term]]
    ^{:doc/actions [{:action "alt-cmd-6", :exec ["SwitchToMode \"Tab\"; GoToTab 6; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P6 [:!SOf] [:term]]
    ^{:doc/actions [{:action "alt-cmd-7", :exec ["SwitchToMode \"Tab\"; GoToTab 7; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P7 [:!SOg] [:term]]
    ^{:doc/actions [{:action "alt-cmd-8", :exec ["SwitchToMode \"Tab\"; GoToTab 8; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P8 [:!SOh] [:term]]
    ^{:doc/actions [{:action "alt-cmd-9", :exec ["SwitchToMode \"Tab\"; GoToTab 9; SwitchToMode \"Normal\";"], :program "zellij"}]} [:!OC#P9 [:!SOi] [:term]]
    ^{:doc/actions [{:action "alt-cmd-0", :exec ["GoToPreviousTab;"], :program "zellij"}]} [:!OC#P0 [:!SOj] [:term]]
    ^{:doc/actions [{}]} [:!OC#P0 [:!OC0]]
    ^{:doc/actions [{}]} [:!OC#Phyphen [:!OChyphen]]
    ^{:doc/actions [{}]} [:!OC#Pequal_sign [:!OCequal_sign]]

    ^{:doc/actions [{}]} [:!OCS#P1 [:!OCS1]]
    ^{:doc/actions [{}]} [:!OCS#P2 [:!OCS2]]
    ^{:doc/actions [{}]} [:!OCS#P3 [:!OCS3]]
    ^{:doc/actions [{}]} [:!OCS#P4 [:!OCS4]]
    ^{:doc/actions [{}]} [:!OCS#P5 [:!OCS5]]
    ^{:doc/actions [{}]} [:!OCS#P6 [:!OCS6]]
    ^{:doc/actions [{}]} [:!OCS#P7 [:!OCS7]]
    ^{:doc/actions [{}]} [:!OCS#P8 [:!OCS8]]
    ^{:doc/actions [{}]} [:!OCS#P9 [:!OCS9]]
    ^{:doc/actions [{}]} [:!OCS#P0 [:!OCS0]]
    ^{:doc/actions [{}]} [:!OCS#Phyphen [:!OCShyphen]]
    ^{:doc/actions [{}]} [:!OCS#Pequal_sign [:!OCSequal_sign]]

  ; alphabetic glyphs
    ^{:doc/actions [{}]} [:!OC#Pa [:!OCa]]
    ^{:doc/actions [{}]} [:!OC#Pb [:!OCb]]
    ^{:doc/actions [{}]} [:!OC#Pc [:!OCc]]
    ^{:doc/actions [{}]} [:!OC#Pd [:!OCd]]
    ^{:doc/actions [{}]} [:!OC#Pe [:!OCe]]
    ^{:doc/actions [{}]} [:!OC#Pf [:!OCf]]
    ^{:doc/actions [{}]} [:!OC#Pg [:!OCg]]
    ^{:doc/actions [{}]} [:!OC#Ph [:!OCh]]
    ^{:doc/actions [{}]} [:!OC#Pi [:!OCi]]
    ^{:doc/actions [{}]} [:!OC#Pj [:!OCj]]
    ^{:doc/actions [{}]} [:!OC#Pk [:!OCk]]
    ^{:doc/actions [{}]} [:!OC#Pl [:!OCl]]
    ^{:doc/actions [{}]} [:!OC#Pm [:!OCm]]
    ^{:doc/actions [{}]} [:!OC#Pn [:!OCn]]
    ^{:doc/actions [{}]} [:!OC#Po [:!OCo]]
    ^{:doc/actions [{}]} [:!OC#Pp [:!OCp]]
    ^{:doc/actions [{}]} [:!OC#Pq [:!OCq]]
    ^{:doc/actions [{}]} [:!OC#Pr [:!OCr]]
    ^{:doc/actions [{}]} [:!OC#Ps [:!OCs]]
    ^{:doc/actions [{}]} [:!OC#Pt [:!OCt]]
    ^{:doc/actions [{}]} [:!OC#Pu [:!OCu]]
    ^{:doc/actions [{}]} [:!OC#Pv [:!OCv]]
    ^{:doc/actions [{}]} [:!OC#Pw [:!OCw]]
    ^{:doc/actions [{}]} [:!OC#Px [:!OCx]]
    ^{:doc/actions [{}]} [:!OC#Py [:!OCy]]
    ^{:doc/actions [{}]} [:!OC#Pz [:!OCz]]
    ^{:doc/actions [{}]} [:!OC#Pright_control [:!OCright_control]]

    ^{:doc/actions [{}]} [:!OCS#Pa [:!OCSa]]
    ^{:doc/actions [{}]} [:!OCS#Pb [:!OCSb]]
    ^{:doc/actions [{}]} [:!OCS#Pc [:!OCSc]]
    ^{:doc/actions [{}]} [:!OCS#Pd [:!OCSd]]
    ^{:doc/actions [{}]} [:!OCS#Pe [:!OCSe]]
    ^{:doc/actions [{}]} [:!OCS#Pf [:!OCSf]]
    ^{:doc/actions [{}]} [:!OCS#Pg [:!OCSg]]
    ^{:doc/actions [{}]} [:!OCS#Ph [:!OCSh]]
    ^{:doc/actions [{}]} [:!OCS#Pi [:!OCSi]]
    ^{:doc/actions [{}]} [:!OCS#Pj [:!OCSj]]
    ^{:doc/actions [{}]} [:!OCS#Pk [:!OCSk]]
    ^{:doc/actions [{}]} [:!OCS#Pl [:!OCSl]]
    ^{:doc/actions [{}]} [:!OCS#Pm [:!OCSm]]
    ^{:doc/actions [{}]} [:!OCS#Pn [:!OCSn]]
    ^{:doc/actions [{}]} [:!OCS#Po [:!OCSo]]
    ^{:doc/actions [{}]} [:!OCS#Pp [:!OCSp]]
    ^{:doc/actions [{}]} [:!OCS#Pq [:!OCSq]]
    ^{:doc/actions [{}]} [:!OCS#Pr [:!OCSr]]
    ^{:doc/actions [{}]} [:!OCS#Ps [:!OCSs]]
    ^{:doc/actions [{}]} [:!OCS#Pt [:!OCSt]]
    ^{:doc/actions [{}]} [:!OCS#Pu [:!OCSu]]
    ^{:doc/actions [{}]} [:!OCS#Pv [:!OCSv]]
    ^{:doc/actions [{}]} [:!OCS#Pw [:!OCSw]]
    ^{:doc/actions [{}]} [:!OCS#Px [:!OCSx]]
    ^{:doc/actions [{}]} [:!OCS#Py [:!OCSy]]
    ^{:doc/actions [{}]} [:!OCS#Pz [:!OCSz]]
    ^{:doc/actions [{}]} [:!OCS#Pright_control [:!OCSright_control]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
