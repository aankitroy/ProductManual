package com.example.scaler_task.pojo

import com.example.scaler_task.R

object QuestionDemo {


    fun getQuestionList(): ArrayList<Question> {
        val questionList: ArrayList<Question> = ArrayList()
        val question1 = Question().apply {
            question = "How to clean the particle sensor"
            answerSteps.add(
                AnswerStep(
                    1,
                    "Pull the back cover and remove it from the appliance",
                    R.drawable.mixer_grinder_step_1_v2
                )
            )
            answerSteps.add(
                AnswerStep(
                    2,
                    "Clean the particle sensor with a cotton swab",
                    R.drawable.mixer_grinder_step_2_v2
                )
            )
            answerSteps.add(
                AnswerStep(
                    3,
                    "Re-attach the back cover",
                    R.drawable.mixer_grinder_step_3_v2
                )
            )
            lastScreenTitle = "Your particle sensor is cleaned now!"
        }

//            val questions1 = Questions().apply {
//            question = "What is the Installation Process"
//            answer =
//                "Make sure the air purifier is unplugged from the electrical outlet before installing the filters.\n" +
//                        " • Make sure the side of the filter with the tag is pointing towards you.\n" +
//                        "1) Pull the top part of the side panel to remove it from the air purifier.\n" +
//                        "2) Press the two clips down (1) and pull the pre-filter towards you (2).\n" +
//                        "3) Remove all filters.\n" +
//                        "4) Remove all packaging materials from the filters.\n" +
//                        "5) Place the thick filter (FY5185) into the air purifier and then the thin Active Carbon filter (FY5182). The model numbers are shown on each filter. \n" +
//                        "6) Attach the pre-filter back into the air purifier.\n" +
//                        " Note \n" +
//                        "• Make sure the side with the two clips is pointing towards you, and that all hooks of the pre-filter are properly attached to the purifier. \n" +
//                        "7)  Reattach the front panel by pressing the top part of the panel onto the top of the air purifier (1). Then, gently push the panel against the body of the air purifier (2).\n" +
//                        "8) Repeat the above steps and assemble the filter for the other side of the air purifier. \n" +
//                        "9)  Wash your hands thoroughly after installing filters.\n"
//        }
//
//        val questions2 = Questions().apply {
//            question = "How to establish a Wi-Fi connection"
//            answer =
//                "Download and install the Philips \"Clean Home+\" app from the App Store or Google Play.\n" +
//                        "2) Put the plug of the air purifier in the power socket and touch to turn on the air purifier. » The Wi-Fi indicator blinks orange for the first time. \n" +
//                        "3) Make sure that your smartphone or tablet is successfully connected to your Wi-Fi network.\n" +
//                        "4) Launch the \"Clean Home+\" app and click on \"Connect a New Device\" or press the \"+\" button on the top of the screen. Follow the on-screen instructions to connect the air purifier to your network. \n" +
//                        "5) After the successful pairing and connection, the Wi-Fi indicator will light up white. If pairing is not successful, consult the troubleshooting section, or the help section in the \"Clean Home+\" App for extensive and up-to-date troubleshooting tips.\n" +
//                        "\n" +
//                        "Note- • This instruction is only valid when the air purifier is being set up for the first time. " +
//                        "If the network has changed or the setup needs to be performed again, consult the section \"Reset the Wi-Fi connection\" on page 7. • If you want to connect more than one air purifier to your smartphone or tablet, you have to do this one by one. Complete the setup of one air purifier before you turn on the other air purifier. • Make sure that the distance between your smartphone or tablet and the air purifier is less than 10 m without any obstructions. • This App supports the latest versions of Android and iOS. Please check www.philips.com/cleanhome for the latest update of supported operating systems and devices"
//        }
//
//        val questions3 = Questions().apply {
//            question = "How we can Reset the Wi-Fi connection"
//            answer ="This applies when the default network to which your purifier is connected has changed.\n" +
//                    " • Reset the Wi-Fi connection when your default network has changed.\n" +
//                    "1) Put the plug of the air purifier in the power socket and touch to turn on the air purifier. \n" +
//                    "2) Touch simultaneously for 3 seconds until you hear a beep. » The air purifier goes to pairing mode. » The Wi-Fi indicator blinks orange. \n" +
//                    "3) Follow steps 4-5 in the \"Setting up the Wi-Fi connection for the first time\" section."
//        }
//
//        val questions4 = Questions().apply {
//            question = "How we can set different modes"
//            answer ="You can choose Pollution mode, Allergen mode, or Bacteria & Virus mode.\n" +
//                    "Allergen mode\n" +
//                    "The extra-sensitive allergen mode is designed to react to even a small change in allergen levels in the surrounding air. • Touch the Auto mode button to select the Allergen mode » Auto and display on the screen. \n" +
//                    "Pollution mode \n" +
//                    "The specially designed pollution mode can effectively remove airborne pollutants such as PM2.5  • Touch the Auto mode button to select Pollution mode. » Auto and display on the screen. \n" +
//                    "Bacteria & Virus mode \n" +
//                    "The Bacteria & Virus mode boosts the airflow to quickly reduce bacteria & viruses.\n" +
//                    "• Touch the Auto mode button to select the Bacteria & Virus mode » Auto and display it on the screen"
//        }
//        val questions5 = Questions().apply {
//            question = "How to set up the child lock"
//            answer ="Touch and hold the child lock button for 3 seconds to activate child lock » The child lock icon displays on the screen. » When the child lock is on, all the other buttons are not responsive.\n" +
//                    "2 Touch and hold the child lock button for 3 seconds again to deactivate the child lock. » The child lock icon disappears"
//        }
//
//
        val question2 = Question().apply {
            question = "स्थापना प्रक्रिया क्या है"
            answerSteps.add(
                AnswerStep(
                    1,
                    "वायु शोधक से निकालने के लिए साइड पैनल के शीर्ष भाग को खींचे।",
                    R.drawable.mixer_grinder_step_1
                )
            )
            answerSteps.add(
                AnswerStep(
                    2,
                    "दो क्लिप को नीचे दबाएं (1) और प्री-फिल्टर को अपनी ओर खींचें (2)।",
                    R.drawable.mixer_grinder_step_1
                )
            )
            answerSteps.add(
                AnswerStep(
                    3,
                    "सभी फिल्टर हटा दें।",
                    R.drawable.mixer_grinder_step_2
                )
            )
            answerSteps.add(
                AnswerStep(
                    4,
                    "फिल्टर से सभी पैकेजिंग सामग्री को हटा दें।",
                    R.drawable.mixer_grinder_step_1
                )
            )
            answerSteps.add(
                AnswerStep(
                    5,
                    "थिक फिल्टर (FY5185) को एयर प्यूरीफायर में और फिर थिन एक्टिव कार्बन फिल्टर (FY5182) में रखें।",
                    R.drawable.mixer_grinder_step_1
                )
            )
            lastScreenTitle = "आपका फ़िल्टर अब स्थापित हो गया है!"
        }

        val question3 = Question().apply {
            question = "How to clean the surface of the filter?"
            answerSteps.add(
                AnswerStep(
                    1,
                    "Pull the back cover and remove it from the appliance",
                    R.drawable.mixer_grinder_step_1_v2
                )
            )
            answerSteps.add(
                AnswerStep(
                    2,
                    "Pull the filter from the appliance",
                    R.drawable.mixer_grinder_open_v2
                )
            )
            answerSteps.add(
                AnswerStep(
                    3,
                    "Clean the surface of the filter with a vacuum cleaner",
                    R.drawable.mixer_clean_surface_v2
                )
            )

            answerSteps.add(
                AnswerStep(
                    4,
                    "Pull the filter back into the appliance",
                    R.drawable.mixer_filter_back_v2
                )
            )
            lastScreenTitle = "Your filter is cleaned now!"
        }

        val question4 = Question().apply {
            question = "How to change the day mode setting?"
            answerSteps.add(
                AnswerStep(
                    1,
                    "Auto mode : In Auto mode, the dual-sensor senses the air quality in real-time and the appliance automatically adjusts the fan speed in accordance with the ambient air quality. The control panel can automatically adjust display screen brightness according to the ambient light.",
                    R.drawable.daymode_setting_step_1
                )
            )
            answerSteps.add(
                AnswerStep(
                    2,
                    "Manual Speed : In manual mode, the Air Purifier operates on Speed 1 or Speed 2.",
                    R.drawable.daymode_setting_step_2
                )
            )
            answerSteps.add(
                AnswerStep(
                    3,
                    "Turbo mode : In Turbo mode, the air purifier operates on the highest speed.",
                    R.drawable.daymode_setting_step_3
                )
            )
            lastScreenTitle = "Your day mode setting is changed now!"
        }

        val question5 = Question().apply {
            question = "How to reset the filter?"
            answerSteps.add(
                AnswerStep(
                    1,
                    "Put the power plug back in to the socket",
                    R.drawable.reset_step_1
                )
            )
            answerSteps.add(
                AnswerStep(
                    2,
                    "Within 15 Seconds after Power on, touch and hold and button for 3 seconds to Reset the filter lifetime counter.\n",
                    R.drawable.reset_step_2
                )
            )
            lastScreenTitle = "Your filter is reset now!"
        }

        val question6 = Question().apply {
            question = "What is the installation process?"
            answerSteps.add(
                AnswerStep(
                    1,
                    "Pull the back cover and remove it from the appliance.",
                    R.drawable.step_1
                )
            )
            answerSteps.add(
                AnswerStep(
                    2,
                    "Pull the filter from the appliance",
                    R.drawable.step_2
                )
            )
            answerSteps.add(
                AnswerStep(
                    3,
                    "Remove all packaging materials from the air purification filter",
                    R.drawable.step_3
                )
            )
            answerSteps.add(
                AnswerStep(
                    4,
                    "Put the filter back into the appliance",
                    R.drawable.step_4
                )
            )
            answerSteps.add(
                AnswerStep(
                    5,
                    "Reattach the back cover",
                    R.drawable.step_5
                )
            )
            lastScreenTitle = "Your filter is installed now!"
        }

//        val questions7 = Questions().apply {
//            question = "वाई-फाई कनेक्शन कैसे स्थापित करें"
//            answer ="ऐप स्टोर या Google Play से Philips \"क्लीन होम\" ऐप डाउनलोड और इंस्टॉल करें।\n" +
//                    "2) एयर प्यूरीफायर का प्लग पावर सॉकेट में लगाएं और एयर प्यूरीफायर चालू करने के लिए टच करें। » वाई-फाई संकेतक पहली बार नारंगी को झपकाता है।\n" +
//                    "3) सुनिश्चित करें कि आपका स्मार्टफोन या टैबलेट आपके वाई-फाई नेटवर्क से सफलतापूर्वक जुड़ा है।\n" +
//                    "4) \"क्लीन होम\" ऐप लॉन्च करें और \"कनेक्ट ए न्यू डिवाइस\" पर क्लिक करें या स्क्रीन के शीर्ष पर \"\" बटन दबाएं। एआई को जोड़ने के लिए ऑन-स्क्रीन निर्देशों का पालन करें\n"
//        }
//
//        val questions8 = Questions().apply {
//            question = "वाई-फाई कनेक्शन कैसे स्थापित करें"
//            answer ="ऐप स्टोर या Google Play से Philips \"क्लीन होम\" ऐप डाउनलोड और इंस्टॉल करें।\n" +
//                    "2) एयर प्यूरीफायर का प्लग पावर सॉकेट में लगाएं और एयर प्यूरीफायर चालू करने के लिए टच करें। » वाई-फाई संकेतक पहली बार नारंगी को झपकाता है।\n" +
//                    "3) सुनिश्चित करें कि आपका स्मार्टफोन या टैबलेट आपके वाई-फाई नेटवर्क से सफलतापूर्वक जुड़ा है।\n" +
//                    "4) \"क्लीन होम\" ऐप लॉन्च करें और \"कनेक्ट ए न्यू डिवाइस\" पर क्लिक करें या स्क्रीन के शीर्ष पर \"\" बटन दबाएं। एआई को जोड़ने के लिए ऑन-स्क्रीन निर्देशों का पालन करें\n"
//        }
//        val questions9 = Questions().apply {
//            question = "हम वाई-फाई कनेक्शन को कैसे रीसेट कर सकते हैं"
//            answer ="यह तब लागू होता है जब डिफॉल्ट नेटवर्क जिससे आपका प्यूरीफायर जुड़ा है, बदल गया है।\n" +
//                    " • जब आपका डिफॉल्ट नेटवर्क बदल जाए तो वाई-फाई कनेक्शन को रीसेट करें।\n" +
//                    "1) एयर प्यूरीफायर का प्लग पावर सॉकेट में लगाएं और एयर प्यूरीफायर चालू करने के लिए टच करें।\n" +
//                    "2) एक साथ 3 सेकंड के लिए स्पर्श करें जब तक कि आप एक बीप न सुनें। » एयर प्यूरीफायर पेयरिंग मोड में चला जाता है। » वाई-फाई संकेतक नारंगी को झपकाता है।\n" +
//                    "3) \"पहली बार वाई-फाई कनेक्शन सेट करना\" खंड में चरण 4-5 का पालन करें।\n"
//        }
//        val questions10 = Questions().apply {
//            question = "हम विभिन्न मोड कैसे सेट कर सकते हैं"
//            answer ="अतिरिक्त संवेदनशील एलर्जेन मोड को आसपास की हवा में एलर्जेन के स्तर में एक छोटे से बदलाव पर भी प्रतिक्रिया करने के लिए डिज़ाइन किया गया है। • एलर्जेन मोड »स्वत: का चयन करने के लिए ऑटो मोड बटन को स्पर्श करें और स्क्रीन पर प्रदर्शित करें।\n" +
//                    "प्रदूषण मोड\n" +
//                    "विशेष रूप से डिज़ाइन किया गया प्रदूषण मोड PM2.5 जैसे वायुजनित प्रदूषकों को प्रभावी ढंग से हटा सकता है • प्रदूषण मोड का चयन करने के लिए ऑटो मोड बटन को स्पर्श करें। » स्क्रीन पर ऑटो और डिस्प्ले।\n"
//        }

        questionList.add(question1)
//        questionList.add(questions2)
//        questionList.add(questions3)
//        questionList.add(questions4)
//        questionList.add(questions5)
        questionList.add(question2)
        questionList.add(question3)
        questionList.add(question4)
        questionList.add(question5)
        questionList.add(question6)
//        questionList.add(questions7)
//        questionList.add(questions8)
//        questionList.add(questions9)
//        questionList.add(questions10)
        return questionList
    }
}