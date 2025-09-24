;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Q
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.q
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "q.edn")

(defn q []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Q mode"
   :rules
   [:q-mode
  ;; find-till-char / repeat
    ^{:doc/actions [{:action "find char forward", :exec ["normal_mode", "till_prev_char", "MODE"], :program "helix-common"}]} [:#Pleft_arrow [:!Tpage_up] [:term]]
    ^{:doc/actions [{:action "find char backward", :exec ["normal_mode", "find_till_char", "MODE"], :program "helix-common"}]} [:#Pright_arrow [:!Tpage_down] [:term]]
 ; TODO: update temp mapping
    ^{:doc/actions [{:action "repeat last motion", :exec ["repeat_last_motion"], :program "helix-common"}]} [:#Pspacebar [:f18] [:term]]

  ;; comment navigation
    ^{:doc/actions [{:action "goto prev comment", :exec ["goto_prev_comment"], :program "helix-common"}]} [:#Pup_arrow [:!Thome] [:term]]
    ^{:doc/actions [{:action "goto next comment", :exec ["goto_next_comment"], :program "helix-common"}]} [:#Pdown_arrow [:!Tend] [:term]]

  ; language server
    ^{:doc/actions [{:action "rename symbol", :exec ["rename_symbol"], :program "helix-common"}]} [:#Pn [:!TSf11] [:term]]
    ^{:doc/actions [{:action "symbol picker", :exec ["symbol_picker"], :program "helix-common"}]} [:#Preturn_or_enter [:!TSf13] [:term]]
    ^{:doc/actions [{:action "goto references", :exec ["goto_reference"], :program "helix-common"}]} [:#Pr [:!Tf16] [:term]]
    ^{:doc/actions [{:action "goto definition", :exec ["goto_definition"], :program "helix-common"}]} [:#Pd [:!Tf17] [:term]]
    ^{:doc/actions [{:action "goto implementation", :exec ["goto_implementation"], :program "helix-common"}]} [:#Pi [:!TSf16] [:term]]
    ^{:doc/actions [{:action "goto type definition", :exec ["goto_type_definition"], :program "helix-common"}]} [:#Pt [:!TSf17] [:term]]

    ^{:doc/actions [{:action "goto definition", :exec ["vsplit", "goto_definition"], :program "helix-common"}]} [:#Pright_shift [:!Tf18] [:term]]
    ^{:doc/actions [{:action "goto type definition", :exec ["vsplit", "goto_type_definition"], :program "helix-common"}]} [:#Pright_option [:!Tf19] [:term]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (q)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

