;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lopt
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lopt.edn")

(defn lopt []

; TODO: finish sequences
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto prev word",     :exec ["move_prev_word_start", "collapse_selection"]}]}               [(c/mk c/ilopt c/al)   [:!Ob]            :term]
    ^{:doc/actions [{:program c/hc,    :action "goto next word",     :exec ["move_next_word_end", "collapse_selection"]}]}                 [(c/mk c/ilopt c/ar)  [:!Of]            :term]
    ^{:doc/actions [{:program c/hc,    :action "goto prev para",     :exec ["goto_prev_paragraph", "collapse_selection"]}
                    {:program c/lg,    :action "goto prev page",     :exec ["prevPage"]}
                    {:program c/mc,    :action "goto prev para",     :exec ["ParagraphPrevious"]}]}                                        [(c/mk c/ilopt c/au)     [:page_up]        :term]
    ^{:doc/actions [{:program c/hc,    :action "goto next para",     :exec ["goto_next_paragraph", "collapse_selection"]}
                    {:program c/lg,    :action "goto next page",     :exec ["nextPage"]}
                    {:program c/mc,    :action "goto next para",     :exec ["ParagraphNext"]}]}                                            [(c/mk c/ilopt c/ad)   [:page_down]      :term]

    ^{:doc/actions [{:program c/hc,    :action "select prev word",   :exec ["extend_prev_word_start"]}
                    {:program c/mc,    :action "select prev word",   :exec ["SelectWordLeft"]}]}                                           [(c/mk c/ilopts c/al)  [:!OSleft_arrow]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select next word",   :exec ["extend_next_word_end"]}
                    {:program c/mc,    :action "select next word",   :exec ["SelectWordRight"]}]}                                          [(c/mk c/ilopts c/ar) [:!OSright_arrow] :term]
    ^{:doc/actions [{:program c/hi,    :action "select prev para",   :exec ["select_mode", "goto_prev_paragraph", "insert_mode"]}
                    {:program c/hn,    :action "select prev para",   :exec ["select_mode", "goto_prev_paragraph", "normal_mode"]}
                    {:program c/hs,    :action "select prev para",   :exec ["select_mode", "goto_prev_paragraph", "select_mode"]}
                    {:program c/mc,    :action "select prev para",   :exec ["StartOfLine,SelectLine,ParagraphPrevious,SelectWordRight"]}]} [(c/mk c/ilopts c/au)    [:!OSup_arrow]    :term]
    ^{:doc/actions [{:program c/hi,    :action "select next para",   :exec ["select_mode", "goto_next_paragraph", "append_mode"]}
                    {:program c/hn,    :action "select next para",   :exec ["select_mode", "goto_next_paragraph", "normal_mode"]}
                    {:program c/hs,    :action "select next para",   :exec ["select_mode", "goto_next_paragraph", "select_mode"]}
                    {:program c/mc,    :action "select next para",   :exec ["StartOfLine,SelectLine,ParagraphNext,SelectWordRight"]}]}     [(c/mk c/ilopts c/ad)  [:!OSdown_arrow]  :term]

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
    ^{:doc/actions [{:program c/hc,    :action "format",             :exec [":format"]}]}          [(c/mk c/ilopt "a") [:!Of1] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "b")     [:!Of2] :term]
    ^{:doc/actions [{:program c/hc,    :action "copy",               :exec ["yank_to_clipboard"]}
                    {:program c/mc,    :action "copy",               :exec ["Copy"]}]}             [(c/mk c/ilopt "c") [:!Of4] :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi",        :exec ["search_selection", "extend_search_next"]}
                    {:program c/mc,    :action "spawn multi",        :exec ["SpawnMultiCursor"]}]} [(c/mk c/ilopt "d") [:!Of5] :term]
    ^{:doc/actions [{:program c/hc,    :action "toggle comments",    :exec ["toggle_comments"]}
                    {:program c/mc,    :action "toggle comments",    :exec ["lua:comment.comment"]}]} [(c/mk c/ilopt "e") [:!Of6] :term]
    ^{:doc/actions [{:program c/hc,    :action "search",             :exec ["search"]}
                    {:program c/mc,    :action "search",             :exec ["FindLiteral"]}]}      [(c/mk c/ilopt "f") [:!Of7] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "g")     [:!Of8] :term]
    ^{:doc/actions [{:program c/hc,    :action "find prev",          :exec ["search_prev"]}
                    {:program c/mc,    :action "find prev",          :exec ["FindPrevious"]}]}     [(c/mk c/ilopt "h") [:!Of9] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "i")     [:!Of10] :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi down",   :exec ["normal_mode", "copy_selection_on_next_line", "MODE"]}
                    {:program c/mc,    :action "spawn multi down",   :exec ["SpawnMultiCursorDown"]}]} [(c/mk c/ilopt "j") [:!Tf1] :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi up",     :exec ["normal_mode", "copy_selection_on_prev_line", "MODE"]}
                    {:program c/mc,    :action "spawn multi up",     :exec ["SpawnMultiCursorUp"]}]} [(c/mk c/ilopt "k") [:!Tf2] :term]
    ^{:doc/actions [{:program c/hc,    :action "find next",          :exec ["search_next"]}
                    {:program c/mc,    :action "find next",          :exec ["FindNext"]}]}         [(c/mk c/ilopt "l") [:!Tf4] :term]
    ^{:doc/actions [{:program c/hc,    :action "toggle macro",       :exec ["record_macro"]}
                    {:program c/mc,    :action "toggle macro",       :exec ["ToggleMacro"]}]}      [(c/mk c/ilopt "m") [:!Tf5] :term]
    ^{:doc/actions [{:program c/hc,    :action "new buffer",         :exec [":new"]}
                    {:program c/mc,    :action "new buffer",         :exec ["AddTab"]}]}           [(c/mk c/ilopt "n") [:!Tf6] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "o")     [:!Tf7] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "p")     [:!Tf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "quit",               :exec ["normal_mode", ":quit-all"]}
                    {:program c/mc,    :action "quit",               :exec ["QuitAll"]}]}          [(c/mk c/ilopt "q") [:!Tf9] :term]
    ^{:doc/actions [{:program c/hc,    :action "reload buffers",     :exec ["normal_mode", ":reload-all", "MODE"]}]} [(c/mk c/ilopt "r") [:!Tf10] :term]
    ^{:doc/actions [{:program c/hc,    :action "save & quit",        :exec ["normal_mode", ":write-quit-all"]}
                    {:program c/mc,    :action "save & quit",        :exec ["Save,QuitAll"]}]}     [(c/mk c/ilopt "s") [:!OTf1] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "t")     [:!OTf2] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopt "u")     [:!OTf4] :term]
    ^{:doc/actions [{:program c/hc,    :action "paste",              :exec ["paste_clipboard_after"]}
                    {:program c/mc,    :action "paste",              :exec ["Paste"]}]}            [(c/mk c/ilopt "v") [:!OTf5] :term]
    ^{:doc/actions [{:program c/hc,    :action "save",               :exec ["normal_mode", ":write"]}
                    {:program c/mc,    :action "save",               :exec ["Save"]}]}             [(c/mk c/ilopt "w") [:!OTf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "cut",                :exec ["yank_to_clipboard", "delete_selection_noyank"]}
                    {:program c/mc,    :action "cut",                :exec ["Cut"]}]}              [(c/mk c/ilopt "x") [:!OTf7] :term]
    ^{:doc/actions [{:program c/hc,    :action "skip multi",         :exec ["search_selection", "search_next"]}
                    {:program c/mc,    :action "skip multi",         :exec ["SkipMultiCursor"]}]}  [(c/mk c/ilopt "y") [:!OTf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "remove multi",       :exec ["search_selection", "search_prev"]}
                    {:program c/mc,    :action "remove multi",       :exec ["RemoveMultiCursor"]}]} [(c/mk c/ilopt "z") [:!OTf9] :term]
    ^{:doc/actions [{:program c/hc,    :action "command mode",       :exec ["command_mode"]}
                    {:program c/mc,    :action "command mode",       :exec ["CommandMode"]}]}      [(c/mk c/ilopt c/rt) [:!OTf10] :term]

    ^{:doc/actions [{:program c/hc,    :action "select all",         :exec ["select_all"]}
                    {:program c/mc,    :action "select all",         :exec ["SelectAll"]}]}        [(c/mk c/ilopts "a") [:!OSf1] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "b")    [:!OSf2] :term]
    ^{:doc/actions [{:program c/hc,    :action "copy line",          :exec ["extend_to_line_bounds", "yank_to_clipboard"]}
                    {:program c/mc,    :action "copy line",          :exec ["CopyLine"]}]}         [(c/mk c/ilopts "c") [:!OSf4] :term]
    ^{:doc/actions [{:program c/hc,    :action "duplicate line",     :exec ["extend_to_line_bounds", "yank", "paste_after", "goto_line_start"]}
                    {:program c/mc,    :action "duplicate line",     :exec ["DuplicateLine"]}]}    [(c/mk c/ilopts "d") [:!OSf5] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "e")    [:!OSf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "global search",      :exec ["global_search"]}
                    {:program c/mc,    :action "global search",      :exec ["Find"]}]}             [(c/mk c/ilopts "f") [:!OSf7] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "g")    [:!OSf8] :term]
    ^{:doc/actions [{}]} [(c/mk c/ilopts "h")    [:!OSf9] :term]
    ^{:doc/actions [{:program c/hc,    :action "indent",             :exec ["indent"]}
                    {:program c/mc,    :action "indent",             :exec ["IndentSelection"]}]}  [(c/mk c/ilopts "i") [:!OSf10] :term]
    ^{:doc/actions [{:program c/hc,    :action "move down",          :exec ["normal_mode", "extend_to_line_bounds", "delete_selection", "paste_after", "MODE"]}
                    {:program c/mc,    :action "move down",          :exec ["MoveLinesDown"]}]}    [(c/mk c/ilopts "j") :!TSf1 :term]
    ^{:doc/actions [{:program c/hc,    :action "move up",            :exec ["normal_mode", "extend_to_line_bounds", "delete_selection", "move_line_up", "paste_before", "MODE"]}
                    {:program c/mc,    :action "move up",            :exec ["MoveLinesUp"]}]}      [(c/mk c/olopt "k") :!TSf2 :term]
    ^{:doc/actions [{:program c/hc,    :action "select line",        :exec ["goto_line_end", "select_mode", "goto_line_start", "MODE"]}
                    {:program c/mc,    :action "select line",        :exec ["SelectLine"]}]}       [(c/mk c/olopt "l") :!TSf4 :term]
    ^{:doc/actions [{:program c/hc,    :action "play macro",         :exec ["replay_macro"]}
                    {:program c/mc,    :action "play macro",         :exec ["PlayMacro"]}]}        [(c/mk c/olopt "m") :!TSf5 :term]
    ^{:doc/actions [{}]} [(c/mk c/olopt "n")     [:!TSf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "unindent",           :exec ["unindent"]}
                    {:program c/mc,    :action "unindent",           :exec ["OutdentSelection"]}]} [(c/mk c/ilopts "o") :!TSf7 :term]
    ^{:doc/actions [{}]} [(c/mk c/olopt "p")     [:!TSf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "launch lazygit",     :exec [":sh zellij run --name lazygit --close-on-exit --floating --pinned true --height 100 --width 140 --x 0 --y 0 -- lazygit"]}]} [(c/mk c/ilopts "q") :!TSf9 :term]
    ^{:doc/actions [{:program c/hc,    :action "launch serpl",       :exec [":sh zellij run --name serpl --close-on-exit --floating --pinned true --height 100 --width 140 --x 0 --y 0 -- serpl"]}]} [(c/mk c/ilopts "r") :!TSf10 :term]
    ^{:doc/actions [{}]} [(c/mk c/olopt "s")     [:!OTSf1] :term]
    ^{:doc/actions [{:program c/hc,    :action "compile todo",       :exec [":sh just todor"]}]}   [(c/mk c/ilopts "t") :!OTSf2 :term]
    ^{:doc/actions [{}]} [(c/mk c/olopt "u")     [:!OTSf4] :term]
    ^{:doc/actions [{}]} [(c/mk c/olopt "v")     [:!OTSf5] :term]
    ^{:doc/actions [{:program c/hc,    :action "launch watch",       :exec [":sh zellij run --name canvas --close-on-exit --floating --pinned true --height 20 --width 40 --x 100 --y 0 -- just watch"]}]} [(c/mk c/ilopts "w") :!OTSf6 :term]
    ^{:doc/actions [{:program c/hc,    :action "cut line",           :exec ["extend_to_line_bounds", "delete_selection_noyank"]}
                    {:program c/mc,    :action "cut line",           :exec ["CutLine"]}]}          [(c/mk c/ilopts "x") :!OTSf7 :term]
    ^{:doc/actions [{}]} [(c/mk c/olopt "y")     [:!OTSf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "yank diagnostic",    :exec [":yank-diagnostic"]}]} [(c/mk c/ilopts "z") :!OTSf9 :term]
    ^{:doc/actions [{:program c/hc,    :action "launch shell",       :exec [":sh zellij run --name canvas --close-on-exit --floating --height 100 --width 140 --x 0 --y 0 -- zsh"]}
                    {:program c/mc,    :action "shell mode",         :exec ["ShellMode"]}]}        [(c/mk c/ilopts c/rt) :!OTSf10 :term]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lopt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
