;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns config.zellij)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def pane-left               ["MoveFocus \"Left\";"])
(def pane-right              ["MoveFocus \"Right\";"])
(def pane-up                 ["MoveFocus \"Up\";"])
(def pane-down               ["MoveFocus \"Down\";"])
(def pane-close              ["CloseFocus;"])
(def pane-focus              ["ToggleFocusFullscreen;"])
(def pane-new-right          ["NewPane \"Right\";"])
(def pane-new-down           ["NewPane \"Down\";"])
(def rename-pane-mode        ["SwitchToMode \"RenamePane\"; PaneNameInput 0;"])
(def last-pane               ["SwitchFocus;"])
(def pane-picker             ["LaunchOrFocusPlugin \"zellij-pane-picker\" { floating true; move_to_focused_tab true; }; }"])

(def move-left               ["MovePane \"Left\";"])
(def move-rigth              ["MovePane \"Right\";"])
(def move-up                 ["MovePane \"Up\";"])
(def move-down               ["MovePane \"Down\";"])
(def size-inc                ["Resize \"Increase\";"])
(def size-dec                ["Resize \"Decrease\";"])
(def toggle-pin              ["TogglePanePinned;"])
(def toggle-float            ["ToggleFloatingPanes;"])
(def break-pane              ["BreakPane;"])
(def toggle-sync             ["ToggleActiveSyncTab;"])
(def toggle-embed            ["TogglePaneEmbedOrFloating;"])
(def locked-mode             ["SwitchToMode \"Locked\";"])

(def plug-room               ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/room.wasm\" { floating true; ignore_case true; };"])
(def history                 ["EditScrollback; SwitchToMode \"Normal\";"])

(def prev-tab                ["GoToPreviousTab;"])
(def next-tab                ["GoToNextTab;"])
(def scroll-up               ["ScrollUp;"])
(def scroll-down             ["ScrollDown;"])
(def swap-tab-left           ["MoveTab \"Left\";"])
(def swap-tab-right          ["MoveTab \"Right\";"])
(def close-tab               ["CloseTab;"])
(def new-tab                 ["NewTab;"])
(def entersearch-mode        ["SwitchToMode \"EnterSearch\"; SearchInput 0;"])
(def rename-tab-mode         ["SwitchToMode \"RenameTab\"; TabNameInput 0;"])
(def last-tab                ["ToggleTab;"])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def e-page-up               ["PageScrollUp;"])
(def e-page-down             ["PageScrollDown;"])

(def e-half-down             ["HalfPageScrollDown;"])
(def e-half-up               ["HalfPageScrollUp;"])

(def p-abort-rename          ["UndoRenamePane; SwitchToMode \"Normal\";"])

(def t-abort-rename          ["UndoRenameTab; SwitchToMode \"Normal\";"])

(def z-normal-mode           ["SwitchToMode \"Normal\";"])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
