;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lopt
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lopt.edn")

(def hc-prev-word        ["move_prev_word_start", "collapse_selection"])
(def hc-next-word        ["move_next_word_end", "collapse_selection"])

(def hc-prev-para        ["goto_prev_paragraph"  "collapse_selection"])
(def mc-prev-para        ["ParagraphPrevious"])
(def hc-next-para        ["goto_next_paragraph"  "collapse_selection"])
(def mc-next-para        ["ParagraphNext"])

(def hc-select-prev-word ["extend_prev_word_start"])
(def mc-select-prev-word ["SelectWordLeft"])
(def hc-select-next-word ["extend_next_word_end"])
(def mc-select-next-word ["SelectWordRight"])

(def hi-select-prev-para ["select_mode" "goto_prev_paragraph" "insert_mode"])
(def hn-select-prev-para ["select_mode" "goto_prev_paragraph" "normal_mode"])
(def hs-select-prev-para ["select_mode" "goto_prev_paragraph" "select_mode"])
(def mc-select-prev-para ["StartOfLine,SelectLine,ParagraphPrevious,SelectWordRight"])

(def hi-select-next-para ["select_mode" "goto_next_paragraph" "append_mode"])
(def hn-select-next-para ["select_mode" "goto_next_paragraph" "normal_mode"])
(def hs-select-next-para ["select_mode" "goto_next_paragraph" "select_mode"])
(def mc-select-next-para ["StartOfLine,SelectLine,ParagraphNext,SelectWordRight"])

(def hc-format           [":format"])
(def hc-copy             ["yank_to_clipboard"])
(def mc-copy             ["Copy"])
(def hc-spawn-multi      ["search_selection", "extend_search_next"])
(def mc-spawn-multi      ["SpawnMultiCursor"])
(def hc-toggle-com       ["toggle_comments"])
(def mc-toggle-com       ["lua:comment.comment"])
(def hc-search           ["search"])
(def mc-search           ["FindLiteral"])
(def hc-find-prev        ["search_prev"])
(def mc-find-prev        ["FindPrevious"])
(def hc-spawn-down       ["normal_mode", "copy_selection_on_next_line", "MODE"])
(def mc-spawn-down       ["SpawnMultiCursorDown"])
(def hc-spawn-up         ["normal_mode", "copy_selection_on_prev_line", "MODE"])
(def mc-spawn-up         ["SpawnMultiCursorUp"])
(def hc-find-next        ["search_next"])
(def mc-find-next        ["FindNext"])
(def hc-record           ["record_macro"])
(def mc-record           ["ToggleMacro"])
(def hc-new              [":new"])
(def mc-new              ["AddTab"])
(def hc-quit             ["normal_mode", ":quit-all"])
(def mc-quit             ["QuitAll"])
(def hc-reload           ["normal_mode", ":reload-all", "MODE"])
(def hc-write-quit       ["normal_mode", ":write-quit-all"])
(def mc-write-quit       ["Save,QuitAll"])
(def hc-paste            ["paste_clipboard_after"])
(def mc-paste            ["Paste"])
(def hc-write            ["normal_mode", ":write"])
(def mc-write            ["Save"])
(def hc-cut              ["yank_to_clipboard", "delete_selection_noyank"])
(def mc-cut              ["Cut"])
(def hc-skip-multi       ["search_selection", "search_next"])
(def mc-skip-multi       ["SkipMultiCursor"])
(def hc-rm-multi         ["search_selection", "search_prev"])
(def mc-rm-multi         ["RemoveMultiCursor"])
(def hc-cmd              ["command_mode"])
(def mc-cmd              ["CommandMode"])

(def hc-select-all       ["select_all"])
(def mc-select-all       ["SelectAll"])
(def hc-copy-line        ["extend_to_line_bounds", "yank_to_clipboard"])
(def mc-copy-line        ["CopyLine"])
(def hc-dup-line         ["extend_to_line_bounds", "yank", "paste_after", "goto_line_start"])
(def mc-dup-line         ["DuplicateLine"])
(def hc-global-search    ["global_search"])
(def mc-global-search    ["Find"])
(def hc-indent           ["indent"])
(def mc-indent           ["IndentSelection"])
(def hc-line-down        ["normal_mode", "extend_to_line_bounds", "delete_selection", "paste_after", "MODE"])
(def mc-line-down        ["MoveLinesDown"])
(def hc-line-up          ["normal_mode", "extend_to_line_bounds", "delete_selection", "move_line_up", "paste_before", "MODE"])
(def mc-line-up          ["MoveLinesUp"])
(def hc-select-line      ["goto_line_end", "select_mode", "goto_line_start", "MODE"])
(def mc-select-line      ["SelectLine"])
(def hc-play             ["replay_macro"])
(def mc-play             ["PlayMacro"])
(def hc-unindent         ["unindent"])
(def mc-unindent         ["OutdentSelection"])
(def hc-lazygit          [":sh zellij run --name lazygit --pinned true  --close-on-exit --floating --height 100 --width 140 --x   0 --y   0 -- lazygit"])
(def hc-serpl            [":sh zellij run --name serpl   --pinned true  --close-on-exit --floating --height 100 --width 140 --x   0 --y   0 -- serpl"])
(def hc-todor            [":sh just todor"])
(def hc-watch            [":sh zellij run --name canvas  --pinned true  --close-on-exit --floating --height  20 --width  40 --x 100 --y   0 -- just watch"])
(def hc-cut-line         ["extend_to_line_bounds", "delete_selection_noyank"])
(def mc-cut-line         ["CutLine"])
(def hc-copy-diag        [":yank-diagnostic"])
(def hc-shell            [":sh zellij run --name canvas  --pinned false --close-on-exit --floating --height 100 --width 140 --x   0 --y   0 -- zsh"])
(def mc-shell            ["ShellMode"])

(defn lopt []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto prev word",     :exec hc-prev-word}]}         [(c/mk c/ilopt c/al) [:!Ob] :term]
    ^{:doc/actions [{:program c/hc,    :action "goto next word",     :exec hc-next-word}]}         [(c/mk c/ilopt c/ar) [:!Of] :term]
    ^{:doc/actions [{:program c/hc,    :action "goto prev para",     :exec hc-prev-para}
                    {:program c/lg,    :action "goto prev page",     :exec ["prevPage"]}
                    {:program c/mc,    :action "goto prev para",     :exec mc-prev-para}]}         [(c/mk c/ilopt c/au) [c/kpu] :term]
    ^{:doc/actions [{:program c/hc,    :action "goto next para",     :exec hc-next-para}
                    {:program c/lg,    :action "goto next page",     :exec ["nextPage"]}
                    {:program c/mc,    :action "goto next para",     :exec mc-next-para}]}         [(c/mk c/ilopt c/ad) [c/kpd] :term]

    ^{:doc/actions [{:program c/hc,    :action "select prev word",   :exec hc-select-prev-word}
                    {:program c/mc,    :action "select prev word",   :exec mc-select-prev-word}]}  [(c/mk c/ilopts c/al) [c/kosal] :term]
    ^{:doc/actions [{:program c/hc,    :action "select next word",   :exec hc-select-next-word}
                    {:program c/mc,    :action "select next word",   :exec mc-select-next-word}]}  [(c/mk c/ilopts c/ar) [c/kosar] :term]
    ^{:doc/actions [{:program c/hi,    :action "select prev para",   :exec hi-select-prev-para}
                    {:program c/hn,    :action "select prev para",   :exec hn-select-prev-para}
                    {:program c/hs,    :action "select prev para",   :exec hs-select-prev-para}
                    {:program c/mc,    :action "select prev para",   :exec mc-select-prev-para}]}  [(c/mk c/ilopts c/au) [c/kosau] :term]
    ^{:doc/actions [{:program c/hi,    :action "select next para",   :exec hi-select-next-para}
                    {:program c/hn,    :action "select next para",   :exec hn-select-next-para}
                    {:program c/hs,    :action "select next para",   :exec hs-select-next-para}
                    {:program c/mc,    :action "select next para",   :exec mc-select-next-para}]}  [(c/mk c/ilopts c/ad) [c/kosad] :term]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <= `"}]}         [(c/mk c/ilopt c/ob)  [:spacebar :!Scomma :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` >= `"}]}         [(c/mk c/ilopt c/cb)  [:spacebar :!Speriod :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt c/sc)   [(c/mk c/olopt c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt c/qu)   [(c/mk c/olopt c/qu)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` | `"}]}          [(c/mk c/ilopt c/bl)  [:spacebar :!Sbackslash :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` < `"}]}          [(c/mk c/ilopt c/cm)  [:spacebar :!Scomma :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` > `"}]}          [(c/mk c/ilopt c/pe)  [:spacebar :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ? `"}]}          [(c/mk c/ilopt c/sl)  [:spacebar :!Sslash :spacebar]]

    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .<= `"}]}        [(c/mk c/ilopts c/ob) [:spacebar :period :!Scomma :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .>= `"}]}        [(c/mk c/ilopts c/cb) [:spacebar :period :!Speriod :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts c/sc)   [(c/mk c/olopts c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts c/qu)   [(c/mk c/olopts c/qu)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .| `"}]}         [(c/mk c/ilopts c/bl) [:spacebar :period :!Sbackslash :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .< `"}]}         [(c/mk c/ilopts c/cm) [:spacebar :period :!Scomma :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .> `"}]}         [(c/mk c/ilopts c/pe) [:spacebar :period :!Speriod :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts c/sl)   [(c/mk c/olopts c/sl)]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilopt c/db)    [(c/mk c/olopt c/db)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` = `"}]}          [(c/mk c/ilopt c/re)  [:spacebar :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` |> `"}]}         [(c/mk c/ilopt c/rs)  [:spacebar :!Sbackslash :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` * `"}]}          [(c/mk c/ilopt c/ro)  [:spacebar :!S8 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` / `"}]}          [(c/mk c/ilopt c/rc)  [:spacebar :slash :spacebar]]
    ^{:doc/actions [{:program "Alfred",      :action "prompt"}]}                                   [(c/mk c/ilopt c/sp)  [:!OCnon_us_pound]]

    ^{:doc/actions [{}]} [(c/mk c/ilopts c/db)   [(c/mk c/ilopts c/db)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .= `"}]}         [(c/mk c/ilopts c/re) [:spacebar :period :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .|> `"}]}        [(c/mk c/ilopts c/rs) [:spacebar :period :!Sbackslash :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .* `"}]}         [(c/mk c/ilopts c/ro) [:spacebar :period :!S8 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ./ `"}]}         [(c/mk c/ilopts c/rc) [:spacebar :period :slash :spacebar]]
    ^{:doc/actions [{:program "Alfred",      :action "file prompt"}]}                              [(c/mk c/ilopts c/sp) [:!OCSnon_us_pound]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilopt "1")     [(c/mk c/olopt "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "2")     [(c/mk c/olopt "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "3")     [(c/mk c/olopt "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "4")     [(c/mk c/olopt "4")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` % `"}]}          [(c/mk c/ilopt "5")   [:spacebar :!S5 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ^ `"}]}          [(c/mk c/ilopt "6")   [:spacebar :!S6 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` & `"}]}          [(c/mk c/ilopt "7")   [:spacebar :!S7 :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "8")     [(c/mk c/olopt "8")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "9")     [(c/mk c/olopt "9")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "0")     [(c/mk c/olopt "0")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` - `"}]}          [(c/mk c/ilopt c/hy)  [:spacebar :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` + `"}]}          [(c/mk c/ilopt c/eq)  [:spacebar :!Sequal_sign :spacebar]]

    ^{:doc/actions [{}]} [(c/mk c/ilopts "1")    [(c/mk c/olopts "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "2")    [(c/mk c/olopts "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "3")    [(c/mk c/olopts "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "4")    [(c/mk c/olopts "4")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .% `"}]}         [(c/mk c/ilopts "5")  [:spacebar :period :!S5 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .^ `"}]}         [(c/mk c/ilopts "6")  [:spacebar :period :!S6 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .& `"}]}         [(c/mk c/ilopts "7")  [:spacebar :period :!S7 :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "8")    [(c/mk c/olopts "8")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "9")    [(c/mk c/olopts "9")]]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "0")    [(c/mk c/olopts "0")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .- `"}]}         [(c/mk c/ilopts c/hy) [:spacebar :period :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .+ `"}]}         [(c/mk c/ilopts c/eq) [:spacebar :period :!Sequal_sign :spacebar]]

    ; alphabetic glyphs
    ^{:doc/actions [{:program c/hc,    :action "format",             :exec hc-format}]}            [(c/mk c/ilopt "a") [:!Of1] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "b")     [:!Of2] :term]
    ^{:doc/actions [{:program c/hc,    :action "copy",               :exec hc-copy}
                    {:program c/mc,    :action "copy",               :exec mc-copy}]}              [(c/mk c/ilopt "c") [:!Of4] :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi",        :exec hc-spawn-multi}
                    {:program c/mc,    :action "spawn multi",        :exec mc-spawn-multi}]}       [(c/mk c/ilopt "d") [:!Of5] :term]
    ^{:doc/actions [{:program c/hc,    :action "toggle comments",    :exec hc-toggle-com}
                    {:program c/mc,    :action "toggle comments",    :exec mc-toggle-com}]}        [(c/mk c/ilopt "e") [:!Of6] :term]
    ^{:doc/actions [{:program c/hc,    :action "search",             :exec hc-search}
                    {:program c/mc,    :action "search",             :exec mc-search}]}            [(c/mk c/ilopt "f") [:!Of7] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "g")     [:!Of8] :term]
    ^{:doc/actions [{:program c/hc,    :action "find prev",          :exec hc-find-prev}
                    {:program c/mc,    :action "find prev",          :exec mc-find-prev}]}         [(c/mk c/ilopt "h") [:!Of9] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "i")     [:!Of10] :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi down",   :exec hc-spawn-down}
                    {:program c/mc,    :action "spawn multi down",   :exec mc-spawn-down}]}        [(c/mk c/ilopt "j") [:!Tf1] :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi up",     :exec hc-spawn-up}
                    {:program c/mc,    :action "spawn multi up",     :exec mc-spawn-up}]}          [(c/mk c/ilopt "k") [:!Tf2] :term]
    ^{:doc/actions [{:program c/hc,    :action "find next",          :exec hc-find-next}
                    {:program c/mc,    :action "find next",          :exec mc-find-next}]}         [(c/mk c/ilopt "l") [:!Tf4] :term]
    ^{:doc/actions [{:program c/hc,    :action "toggle macro",       :exec hc-record}
                    {:program c/mc,    :action "toggle macro",       :exec mc-record}]}            [(c/mk c/ilopt "m") [:!Tf5] :term]
    ^{:doc/actions [{:program c/hc,    :action "new buffer",         :exec hc-new}
                    {:program c/mc,    :action "new buffer",         :exec mc-new}]}               [(c/mk c/ilopt "n") [:!Tf6] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "o")     [:!Tf7] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "p")     [:!Tf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "quit",               :exec hc-quit}
                    {:program c/mc,    :action "quit",               :exec mc-quit}]}              [(c/mk c/ilopt "q") [:!Tf9] :term]
    ^{:doc/actions [{:program c/hc,    :action "reload buffers",     :exec hc-reload}]}            [(c/mk c/ilopt "r") [:!Tf10] :term]
    ^{:doc/actions [{:program c/hc,    :action "save & quit",        :exec hc-write-quit}
                    {:program c/mc,    :action "save & quit",        :exec mc-write-quit}]}        [(c/mk c/ilopt "s") [:!OTf1] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "t")     [:!OTf2] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "u")     [:!OTf4] :term]
    ^{:doc/actions [{:program c/hc,    :action "paste",              :exec hc-paste}
                    {:program c/mc,    :action "paste",              :exec mc-paste}]}             [(c/mk c/ilopt "v") [:!OTf5] :term]
    ^{:doc/actions [{:program c/hc,    :action "save",               :exec hc-write}
                    {:program c/mc,    :action "save",               :exec mc-write}]}             [(c/mk c/ilopt "w") [:!OTf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "cut",                :exec hc-cut}
                    {:program c/mc,    :action "cut",                :exec mc-cut}]}               [(c/mk c/ilopt "x") [:!OTf7] :term]
    ^{:doc/actions [{:program c/hc,    :action "skip multi",         :exec hc-skip-multi}
                    {:program c/mc,    :action "skip multi",         :exec mc-skip-multi}]}        [(c/mk c/ilopt "y") [:!OTf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "remove multi",       :exec hc-rm-multi}
                    {:program c/mc,    :action "remove multi",       :exec mc-rm-multi}]}          [(c/mk c/ilopt "z") [:!OTf9] :term]
    ^{:doc/actions [{:program c/hc,    :action "command mode",       :exec hc-cmd}
                    {:program c/mc,    :action "command mode",       :exec mc-cmd}]}               [(c/mk c/ilopt c/rt) [:!OTf10] :term]

    ^{:doc/actions [{:program c/hc,    :action "select all",         :exec hc-select-all}
                    {:program c/mc,    :action "select all",         :exec mc-select-all}]}        [(c/mk c/ilopts "a") [:!OSf1] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "b")    [:!OSf2] :term]
    ^{:doc/actions [{:program c/hc,    :action "copy line",          :exec hc-copy-line}
                    {:program c/mc,    :action "copy line",          :exec mc-copy-line}]}         [(c/mk c/ilopts "c") [:!OSf4] :term]
    ^{:doc/actions [{:program c/hc,    :action "duplicate line",     :exec hc-dup-line}
                    {:program c/mc,    :action "duplicate line",     :exec mc-dup-line}]}          [(c/mk c/ilopts "d") [:!OSf5] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "e")    [:!OSf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "global search",      :exec hc-global-search}
                    {:program c/mc,    :action "global search",      :exec mc-global-search}]}     [(c/mk c/ilopts "f") [:!OSf7] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "g")    [:!OSf8] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "h")    [:!OSf9] :term]
    ^{:doc/actions [{:program c/hc,    :action "indent",             :exec hc-indent}
                    {:program c/mc,    :action "indent",             :exec mc-indent}]}            [(c/mk c/ilopts "i") [:!OSf10] :term]
    ^{:doc/actions [{:program c/hc,    :action "move down",          :exec hc-line-down}
                    {:program c/mc,    :action "move down",          :exec mc-line-down}]}         [(c/mk c/ilopts "j") :!TSf1 :term]
    ^{:doc/actions [{:program c/hc,    :action "move up",            :exec hc-line-up}
                    {:program c/mc,    :action "move up",            :exec mc-line-up}]}           [(c/mk c/ilopts "k") :!TSf2 :term]
    ^{:doc/actions [{:program c/hc,    :action "select line",        :exec hc-select-line}
                    {:program c/mc,    :action "select line",        :exec mc-select-line}]}       [(c/mk c/ilopts "l") :!TSf4 :term]
    ^{:doc/actions [{:program c/hc,    :action "play macro",         :exec hc-play}
                    {:program c/mc,    :action "play macro",         :exec mc-play}]}              [(c/mk c/ilopts "m") :!TSf5 :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "n")     [:!TSf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "unindent",           :exec hc-unindent}
                    {:program c/mc,    :action "unindent",           :exec mc-unindent}]}          [(c/mk c/ilopts "o") :!TSf7 :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "p")     [:!TSf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "launch lazygit",     :exec hc-lazygit}]}           [(c/mk c/ilopts "q") :!TSf9 :term]
    ^{:doc/actions [{:program c/hc,    :action "launch serpl",       :exec hc-serpl}]}             [(c/mk c/ilopts "r") :!TSf10 :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "s")     [:!OTSf1] :term]
    ^{:doc/actions [{:program c/hc,    :action "compile todo",       :exec hc-todor}]}             [(c/mk c/ilopts "t") :!OTSf2 :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "u")     [:!OTSf4] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "v")     [:!OTSf5] :term]
    ^{:doc/actions [{:program c/hc,    :action "launch watch",       :exec hc-watch}]}             [(c/mk c/ilopts "w") :!OTSf6 :term]
    ^{:doc/actions [{:program c/hc,    :action "cut line",           :exec hc-cut-line}
                    {:program c/mc,    :action "cut line",           :exec mc-cut-line}]}          [(c/mk c/ilopts "x") :!OTSf7 :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "y")     [:!OTSf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "yank diagnostic",    :exec hc-copy-diag}]}         [(c/mk c/ilopts "z") :!OTSf9 :term]
    ^{:doc/actions [{:program c/hc,    :action "launch shell",       :exec hc-shell}
                    {:program c/mc,    :action "shell mode",         :exec mc-shell}]}             [(c/mk c/ilopts c/rt) :!OTSf10 :term]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lopt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
