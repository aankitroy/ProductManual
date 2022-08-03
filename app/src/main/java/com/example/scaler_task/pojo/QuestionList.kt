package com.example.scaler_task.pojo

object QuestionDemo {


    fun getQuestionList(): ArrayList<Questions> {
        val questionList: ArrayList<Questions> = ArrayList()
        val questions1 = Questions().apply {
            question = "What is the Installation Process"
            answer =
                "Make sure the air purifier is unplugged from the electrical outlet before installing the filters.\n" +
                        " • Make sure the side of the filter with the tag is pointing towards you.\n" +
                        "1) Pull the top part of the side panel to remove it from the air purifier.\n" +
                        "2) Press the two clips down (1) and pull the pre-filter towards you (2).\n" +
                        "3) Remove all filters.\n" +
                        "4) Remove all packaging materials from the filters.\n" +
                        "5) Place the thick filter (FY5185) into the air purifier and then the thin Active Carbon filter (FY5182). The model numbers are shown on each filter. \n" +
                        "6) Attach the pre-filter back into the air purifier.\n" +
                        " Note \n" +
                        "• Make sure the side with the two clips is pointing towards you, and that all hooks of the pre-filter are properly attached to the purifier. \n" +
                        "7)  Reattach the front panel by pressing the top part of the panel onto the top of the air purifier (1). Then, gently push the panel against the body of the air purifier (2).\n" +
                        "8) Repeat the above steps and assemble the filter for the other side of the air purifier. \n" +
                        "9)  Wash your hands thoroughly after installing filters.\n"
        }

        val questions2 = Questions().apply {
            question = "How to establish a Wi-Fi connection"
            answer =
                "Download and install the Philips \"Clean Home+\" app from the App Store or Google Play.\n" +
                        "2) Put the plug of the air purifier in the power socket and touch to turn on the air purifier. » The Wi-Fi indicator blinks orange for the first time. \n" +
                        "3) Make sure that your smartphone or tablet is successfully connected to your Wi-Fi network.\n" +
                        "4) Launch the \"Clean Home+\" app and click on \"Connect a New Device\" or press the \"+\" button on the top of the screen. Follow the on-screen instructions to connect the air purifier to your network. \n" +
                        "5) After the successful pairing and connection, the Wi-Fi indicator will light up white. If pairing is not successful, consult the troubleshooting section, or the help section in the \"Clean Home+\" App for extensive and up-to-date troubleshooting tips.\n" +
                        "\n" +
                        "Note- • This instruction is only valid when the air purifier is being set up for the first time. " +
                        "If the network has changed or the setup needs to be performed again, consult the section \"Reset the Wi-Fi connection\" on page 7. • If you want to connect more than one air purifier to your smartphone or tablet, you have to do this one by one. Complete the setup of one air purifier before you turn on the other air purifier. • Make sure that the distance between your smartphone or tablet and the air purifier is less than 10 m without any obstructions. • This App supports the latest versions of Android and iOS. Please check www.philips.com/cleanhome for the latest update of supported operating systems and devices"
        }

        val questions3 = Questions().apply {
            question = "How we can Reset the Wi-Fi connection"
            answer ="This applies when the default network to which your purifier is connected has changed.\n" +
                    " • Reset the Wi-Fi connection when your default network has changed.\n" +
                    "1) Put the plug of the air purifier in the power socket and touch to turn on the air purifier. \n" +
                    "2) Touch simultaneously for 3 seconds until you hear a beep. » The air purifier goes to pairing mode. » The Wi-Fi indicator blinks orange. \n" +
                    "3) Follow steps 4-5 in the \"Setting up the Wi-Fi connection for the first time\" section."
        }

        val questions4 = Questions().apply {
            question = "How we can set different modes"
            answer ="You can choose Pollution mode, Allergen mode, or Bacteria & Virus mode.\n" +
                    "Allergen mode\n" +
                    "The extra-sensitive allergen mode is designed to react to even a small change in allergen levels in the surrounding air. • Touch the Auto mode button to select the Allergen mode » Auto and display on the screen. \n" +
                    "Pollution mode \n" +
                    "The specially designed pollution mode can effectively remove airborne pollutants such as PM2.5  • Touch the Auto mode button to select Pollution mode. » Auto and display on the screen. \n" +
                    "Bacteria & Virus mode \n" +
                    "The Bacteria & Virus mode boosts the airflow to quickly reduce bacteria & viruses.\n" +
                    "• Touch the Auto mode button to select the Bacteria & Virus mode » Auto and display it on the screen"
        }
        val questions5 = Questions().apply {
            question = "How to set up the child lock"
            answer ="Touch and hold the child lock button for 3 seconds to activate child lock » The child lock icon displays on the screen. » When the child lock is on, all the other buttons are not responsive.\n" +
                    "2 Touch and hold the child lock button for 3 seconds again to deactivate the child lock. » The child lock icon disappears"
        }
        val questions6 = Questions().apply {
            question = "What are the features of a Food Processor"
            answer ="Following are the features-\n" +
                    "• Extra powerful motor \n" +
                    "• Overload protector to protect the motor from burnout \n" +
                    "• Unbreakable FP bowl/cover \n" +
                    "• Safety switch for the safe operation of FP bowl & jars \n" +
                    "• Fruit Filter \n" +
                    "• Citrus & Centrifugal juicer attachment\n" +
                    " • Cord storage attachment"
        }
        val questions7 = Questions().apply {
            question = "What are the parts offered by Food Processor"
            answer ="Following are the parts-\n" +
                    "A. Main Unit \n" +
                    "1. Basic Unit \n" +
                    "2. Rotary Switch \n" +
                    "3. Processor Spindle \n" +
                    "4. Over Load Indicator \n" +
                    "5. Power Indicator \n" +
                    "6. Reset Button \n" +
                    "7. Whipping Switch \n" +
                    "8. Safety Cover \n" +
                    "9. Safety Knob \n" +
                    "10. Spatula \n" +
                    "B. Bowl Assembly \n" +
                    "11. Bowl \n" +
                    "12. Bowl Lid \n" +
                    "13. Feeder Tube \n" +
                    "14. Food Pusher \n" +
                    "15. Spindle Extension [Type Al\n" +
                    "C. Blade Attachment \n" +
                    "16. Kneading Blade \n" +
                    "17. Chopping Blade \n" +
                    "18. Blade Holder \n" +
                    "19. Shredding Blade [Fine] \n" +
                    "20. Shredding Blade [Course) \n" +
                    "21. Slicing Blade \n" +
                    "22. French Fries Blade \n" +
                    "D. Multipurpose jar (1000 ml) \n" +
                    "23. MP jar \n" +
                    "24. Ring\n" +
                    "25. Jar Lid \n" +
                    "26. Handle \n" +
                    "E. Blending jar (1500 ml) \n" +
                    "27. Jar \n" +
                    "28. Jar Lid \n" +
                    "29. Fruit Filter \n" +
                    "30. Bush Plate Assembly \n" +
                    "31. Socket F. \n" +
                    "32. Chutney Jar \n" +
                    "34. Lid G. Juicer\n" +
                    "35. Citrus Bowl \n" +
                    "36. Spindle Extension (Type Bl) \n" +
                    "37. Citrus Cone \n" +
                    "38. Centrifugal Juicer\n" +
                    "39. Juicer Mesh \n" +
                    "40. Juicer Lid \n" +
                    "41. Pusher "
        }
        val questions8 = Questions().apply {
            question = "What are the steps for Kneading and Chopping"
            answer ="Ensure that the food processor unit is unplugged from the power supply.\n" +
                    "2. Insert the safety cover into the processor body aligning the marking on the safety cover with the marking on the processor body.\n" +
                    "3. Turn the safety cover clockwise firmly until it clicks into place and Marking on the safety cover and marking on the processor body are aligned.\n" +
                    "Note: The food processor will not operate unless the safety cover is fitted in its place \n" +
                    "4. Place the processor bowl onto the processor body aligning the marking on the processor bowl with the marking on the processor body. \n" +
                    "5. Turn the processor bowl clockwise firmly until it clicks into place and mark on the processor bowl respectively are aligned. \n" +
                    "6. When the processor bowl is fitted correctly, the processor bowl handle will be to the right of the food processor assembly. \n" +
                    "7. Place spindle extension [Type Al over the processor spindle. Insert the required blade on to the spindle extension. \n" +
                    "8. Fill in the flour/vegetable in the processor bowl. \n" +
                    "9. Holding the processor bowl lid by the feeder tube fit the lid on the processor bowl and rotate clockwise till the protruding lid tab has clicked into place and the marking [e/ &) are aligned. \n" +
                    "10. Plug in the processor. \n" +
                    "11. Run the Processor at speeds 1& 2. \n" +
                    "12. Add water gradually through the feeder tube till dough forms into a rounded lump.[ln case of Kneading). \n" +
                    "13. Allow it to run for 10 to 15 seconds, or till the desired consistency is achieved. \n" +
                    "14. To remove the dough vegetables stop the processor, press the safety knob and hold the process's handle firmly and turn anticlockwise to unlock. \n" +
                    "15. Lift the processor bowl and unlock the processor lid. \n" +
                    "16. Remove the dough vegetables from the processor bowl; use a spatula if required. \n" +
                    "Notes: \n" +
                    "1. The food processor will not operate unless the safety cover is fitted in its place and the processor lid is locked. \n" +
                    "2. To achieve the best kneading action; apply light edible oil to the inside surface of the food processor bowl. For chopping operation please use a chopping blade instead of a kneading blade as mentioned in the figure. All relevant operations shall remain the same"
        }
        val questions9 = Questions().apply {
            question = "What are the steps to operate Citrus Juicer"
            answer ="Ensure that the processor is unplugged from the power supply. \n" +
                    "2. Insert the safety cover into the processor body aligning marking (T) on the safety cover with (•) marking on the processor body. \n" +
                    "3. Turn the safety cover clockwise firmly until it clicks into place and mark (•)on the safety cover and(•) on the processor body are aligned. \n" +
                    "Note: the food processor will not operate unless the safety cover is fitted in its place.\n" +
                    "4. Place the processor bowl onto the processor body aligning (T) marking on the processor bowl with (•) marking on the processor body. \n" +
                    "5. Turn the processor bowl clockwise firmly until it clicks into place and (e & •) marking on the processor bowl and the Processor body respectively are aligned.\n" +
                    "6. When the processor bowl is fitted correctly, the processor bowl handle will be to the right of the food processor assembly. \n" +
                    "7. Place spindle extension (type B) over the processor spindle. \n" +
                    "8. Fit the citrus bowl into the processor bowl. \n" +
                    "9. Rotate the citrus bowl clockwise till the protruding lid tab has clicked into place and the markings (•!•) are aligned. \n" +
                    "10. Insert the citrus cone into the off-centered spindle. \n" +
                    "11. Plug in the processor. \n" +
                    "12. Place half-cut sweet limes on the citrus cone and hold with a cupped hand. \n" +
                    "13. Run the processor at the minimum speed only. \n" +
                    "14. The juice will be collected in the processor bowl. \n" +
                    "15. The seeds and the pulp will accumulate in the citrus bowl. \n" +
                    "16. Remove the seeds and the pulp as and when it gets filled. \n" +
                    "17. To remove the juice, stop the processor, press the safety knob and hold the processor handle firmly, and turn anticlockwise to unlock. \n" +
                    "18. Lift the processor bowl and unlock the citrus press bowl. \n" +
                    "19. Remove the citrus cone, citrus press bowl, and then the juice from the processor bowl and store in a container. \n" +
                    "NOTE: \n" +
                    "1. Operate the juice at minimum speed. \n" +
                    "2. Pulp and seeds must be removed before it starts spilling under the citrus cone. Empty the seeds and pulp regularly"
        }

        val questions10 = Questions().apply {
            question = "What is Over Load Protetor(OLP)"
            answer ="The OLP is provided in the food processor to protect the motor from burning. \n" +
                    "• When the jar is filled more than what is specified or the liquid is too viscous, the OLP trip indicator illuminates, and the motor steps automatically.\n" +
                    "• When this happens, reduce the quantity of ingredients being ground or add some water. \n" +
                    "• To restart, wait for a while and press the reset button of OLP and then start the processor again. \n" +
                    "NOTE: \n" +
                    "1. The grinder jar will not operate unless the processor bowl is fitted in its place and the processor is locked. \n" +
                    "2. Caution: the stainless steel blades are very sharp, take extreme care while handling"
        }

        val questions11 = Questions().apply {
            question = "स्थापना प्रक्रिया क्या है"
            answer ="फिल्टर लगाने से पहले सुनिश्चित करें कि एयर प्यूरिफायर बिजली के आउटलेट से अनप्लग है।\n" +
                    " • सुनिश्चित करें कि टैग के साथ फ़िल्टर का किनारा आपकी ओर इशारा कर रहा है।\n" +
                    "1) वायु शोधक से निकालने के लिए साइड पैनल के शीर्ष भाग को खींचे।\n" +
                    "2) दो क्लिप को नीचे दबाएं (1) और प्री-फिल्टर को अपनी ओर खींचें (2)।\n" +
                    "3) सभी फिल्टर हटा दें।\n" +
                    "4) फिल्टर से सभी पैकेजिंग सामग्री को हटा दें।\n" +
                    "5) थिक फिल्टर (FY5185) को एयर प्यूरीफायर में और फिर थिन एक्टिव कार्बन फिल्टर (FY5182) में रखें।\n"
        }
        val questions12 = Questions().apply {
            question = "वाई-फाई कनेक्शन कैसे स्थापित करें"
            answer ="ऐप स्टोर या Google Play से Philips \"क्लीन होम\" ऐप डाउनलोड और इंस्टॉल करें।\n" +
                    "2) एयर प्यूरीफायर का प्लग पावर सॉकेट में लगाएं और एयर प्यूरीफायर चालू करने के लिए टच करें। » वाई-फाई संकेतक पहली बार नारंगी को झपकाता है।\n" +
                    "3) सुनिश्चित करें कि आपका स्मार्टफोन या टैबलेट आपके वाई-फाई नेटवर्क से सफलतापूर्वक जुड़ा है।\n" +
                    "4) \"क्लीन होम\" ऐप लॉन्च करें और \"कनेक्ट ए न्यू डिवाइस\" पर क्लिक करें या स्क्रीन के शीर्ष पर \"\" बटन दबाएं। एआई को जोड़ने के लिए ऑन-स्क्रीन निर्देशों का पालन करें\n"
        }

        val questions13 = Questions().apply {
            question = "वाई-फाई कनेक्शन कैसे स्थापित करें"
            answer ="ऐप स्टोर या Google Play से Philips \"क्लीन होम\" ऐप डाउनलोड और इंस्टॉल करें।\n" +
                    "2) एयर प्यूरीफायर का प्लग पावर सॉकेट में लगाएं और एयर प्यूरीफायर चालू करने के लिए टच करें। » वाई-फाई संकेतक पहली बार नारंगी को झपकाता है।\n" +
                    "3) सुनिश्चित करें कि आपका स्मार्टफोन या टैबलेट आपके वाई-फाई नेटवर्क से सफलतापूर्वक जुड़ा है।\n" +
                    "4) \"क्लीन होम\" ऐप लॉन्च करें और \"कनेक्ट ए न्यू डिवाइस\" पर क्लिक करें या स्क्रीन के शीर्ष पर \"\" बटन दबाएं। एआई को जोड़ने के लिए ऑन-स्क्रीन निर्देशों का पालन करें\n"
        }
        val questions14 = Questions().apply {
            question = "हम वाई-फाई कनेक्शन को कैसे रीसेट कर सकते हैं"
            answer ="यह तब लागू होता है जब डिफॉल्ट नेटवर्क जिससे आपका प्यूरीफायर जुड़ा है, बदल गया है।\n" +
                    " • जब आपका डिफॉल्ट नेटवर्क बदल जाए तो वाई-फाई कनेक्शन को रीसेट करें।\n" +
                    "1) एयर प्यूरीफायर का प्लग पावर सॉकेट में लगाएं और एयर प्यूरीफायर चालू करने के लिए टच करें।\n" +
                    "2) एक साथ 3 सेकंड के लिए स्पर्श करें जब तक कि आप एक बीप न सुनें। » एयर प्यूरीफायर पेयरिंग मोड में चला जाता है। » वाई-फाई संकेतक नारंगी को झपकाता है।\n" +
                    "3) \"पहली बार वाई-फाई कनेक्शन सेट करना\" खंड में चरण 4-5 का पालन करें।\n"
        }
        val questions15 = Questions().apply {
            question = "हम विभिन्न मोड कैसे सेट कर सकते हैं"
            answer ="अतिरिक्त संवेदनशील एलर्जेन मोड को आसपास की हवा में एलर्जेन के स्तर में एक छोटे से बदलाव पर भी प्रतिक्रिया करने के लिए डिज़ाइन किया गया है। • एलर्जेन मोड »स्वत: का चयन करने के लिए ऑटो मोड बटन को स्पर्श करें और स्क्रीन पर प्रदर्शित करें।\n" +
                    "प्रदूषण मोड\n" +
                    "विशेष रूप से डिज़ाइन किया गया प्रदूषण मोड PM2.5 जैसे वायुजनित प्रदूषकों को प्रभावी ढंग से हटा सकता है • प्रदूषण मोड का चयन करने के लिए ऑटो मोड बटन को स्पर्श करें। » स्क्रीन पर ऑटो और डिस्प्ले।\n"
        }

        questionList.add(questions1)
        questionList.add(questions2)
        questionList.add(questions3)
        questionList.add(questions4)
        questionList.add(questions5)
        questionList.add(questions6)
        questionList.add(questions7)
        questionList.add(questions8)
        questionList.add(questions9)
        questionList.add(questions10)
        questionList.add(questions11)
        questionList.add(questions12)
        questionList.add(questions13)
        questionList.add(questions14)
        questionList.add(questions15)
        return questionList
    }
}