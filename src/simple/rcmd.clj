;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rcmd

  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "rcmd.edn")

(defn rcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
  ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "save jump",          :exec ["save_selection"]}]}   [:!Q#Pspacebar [:!TSf18] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump backward",      :exec ["jump_backward"]}]}    [:!Q#Pleft_control [:!TShome] :term]
    ^{:doc/actions [{:program c/hc,    :action "jumplist picker",    :exec ["jumplist_picker"]}]}  [:!Q#Pleft_option [:!TSf12] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump forward",       :exec ["jump_forward"]}]}     [:!Q#Pleft_command [:!TSend] :term]

  ; technical glyphs
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %<+% '"}]}       [:!Q#Popen_bracket [:spacebar :!S5 :!Scomma :!Sequal_sign :!S5 :spacebar]]
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %+>% '"}]}       [:!Q#Pclose_bracket [:spacebar :!S5 :!Sequal_sign :!Speriod :!S5 :spacebar]]
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %$% '"}]}        [:!Q#Psemicolon [:spacebar :!S5 :!S4 :!S5 :spacebar]]
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %T>% '"}]}       [:!Q#Pquote [:spacebar :!S5 :!St :!Speriod :!S5 :spacebar]]
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %<>% '"}]}       [:!Q#Pbackslash [:spacebar :!S5 :!Scomma :!Speriod :!S5 :spacebar]]
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %<% '"}]}        [:!Q#Pcomma [:spacebar :!S5 :!Scomma :!S5 :spacebar]]
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %>% '"}]}        [:!Q#Pperiod [:spacebar :!S5 :!Speriod :!S5 :spacebar]]
    ^{:doc/actions [{:program c/r,     :action "tidyverse",          :sequence "' %!>% '"}]}       [:!Q#Pslash [:spacebar :!S5 :!S1 :!Speriod :!S5 :spacebar]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
