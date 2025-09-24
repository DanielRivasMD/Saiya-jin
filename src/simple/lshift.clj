;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SHIFT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lshift
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lshift.edn")

(defn lshift []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Shift Mode"
   :rules
   [;
  ;; Selection & word-wise navigation
    ^{:doc/actions [{:action "select prev char", :exec "extend_char_left", :program "helix-common"}]} [:!S#Pleft_arrow [:!Sleft_arrow]]
    ^{:doc/actions [{:action "select next char", :exec "extend_char_right", :program "helix-common"}]} [:!S#Pright_arrow [:!Sright_arrow]]
    ^{:doc/actions [{:action "select line up", :exec "extend_line_up", :program "helix-common"}]} [:!S#Pup_arrow [:!Sup_arrow]]
    ^{:doc/actions [{:action "select line down", :exec "extend_line_down", :program "helix-common"}]} [:!S#Pdown_arrow [:!Sdown_arrow]]

  ; ^{:doc/actions [{:action "word-prev-start", :exec "move_prev_word_start", :program "helix"}]}
  ; ^{:doc/actions [{:action "word-next-end", :exec "move_next_word_end", :program "helix"}]}
  ; ^{:doc/actions [{:action "prev-paragraph", :exec "goto_prev_paragraph", :program "helix"}]}
  ; ^{:doc/actions [{:action "next-paragraph", :exec "goto_next_paragraph", :program "helix"}]}
  ; ^{:doc/actions [{:action "select-prev-word", :exec "extend_prev_word_start", :program "helix"}]}
  ; ^{:doc/actions [{:action "select-next-word", :exec "extend_next_word_end", :program "helix"}]}

  ; [:!S#Pleft_arrow []]
  ; [:!S#Pright_arrow []]
  ; [:!S#Pup_arrow []]
  ; [:!S#Pdown_arrow []]

  ; [:!S#Pdelete_or_backspace []]
  ; [:!S#Preturn_or_enter []]

    ^{:doc/actions [{:action "delete next char", :exec ["delete_selection_noyank"], :program "helix-common"}]} [:!S#Pright_shift [:delete_forward]]
    ^{:doc/actions [{:action "delete next word", :exec ["delete_word_forward"], :program "helix-common"}
                    {:action "delete next word", :exec ["DeleteWordRight"], :program "micro"}]} [:!S#Pright_option [:!Ed]]
    ^{:doc/actions [{:action "delete line start", :exec ["kill_to_line_end"], :program "helix-common"}
                    {:action "delete line start", :exec ["SelectToEndOfLine,Delete"], :program "micro"}]} [:!S#Pright_command [:!Wk]]
  ; [:!S#Pspacebar []]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lshift)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
