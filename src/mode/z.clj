;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Z
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.z
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "z.edn")

(defn z []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Z mode"
   :rules
   [:z
 ; TODO: add binding
  ; ^{:doc/actions [{:action "goto-last-modification", :exec "goto_last_modification", :program "helix-common"}]}

  ;; undo / redo
    ^{:doc/actions [{:action "undo", :exec ["undo"], :program "helix-common"}
                    {:action "undo", :exec ["Undo"], :program "micro"}]} [:#Pleft_arrow [:!Opage_up] [:term]]
    ^{:doc/actions [{:action "redo", :exec ["redo"], :program "helix-common"}
                    {:action "redo", :exec ["Redo"], :program "micro"}]} [:#Pright_arrow [:!Opage_down] [:term]]

  ;; change navigation
    ^{:doc/actions [{:action "prev change", :exec "goto_prev_change", :program "helix-common"}
                    {:action "prev change", :exec ["DiffPrevious"], :program "micro"}]} [:#Pup_arrow [:!Ohome] [:term]]
    ^{:doc/actions [{:action "next change", :exec "goto_next_change", :program "helix-common"}
                    {:action "next change", :exec ["DiffNext"], :program "micro"}]} [:#Pdown_arrow [:!Oend] [:term]]

  ;; diagnostics: prev / next / picker
    ^{:doc/actions [{:action "goto prev diagnostic", :exec ["goto_prev_diag"], :program "helix-common"}]} [:#Pdelete_or_backspace [:!Tf11] [:term]]
    ^{:doc/actions [{:action "goto next diagnostic", :exec ["goto_next_diag"], :program "helix-common"}]} [:#Pright_shift [:!Tf12] [:term]]
    ^{:doc/actions [{:action "diagnostics picker", :exec ["diagnostics_picker"], :program "helix-common"}]} [:#Preturn_or_enter [:!Tf13] [:term]]
    ^{:doc/actions [{:action "workspace diagnostics picker", :exec ["workspace_diagnostics_picker"], :program "helix-common"}]} [:!S#Preturn_or_enter [:!Tf14] [:term]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (z)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

