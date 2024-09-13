package my.soc.rutgersscheduleofclasses.data

import java.util.Calendar


/**
 * An object to store all maps from codes to full strings
 */
object PromptRepository {

    //maps codes to course levels
    val levels = mapOf<String,String>(
        "U" to "Undergraduate",
        "G" to "Graduate"
    )

    //maps term numbers to term names
    val terms = mapOf<String,String>(
        "1" to "Spring",
        "7" to "Summer",
        "9" to "Fall",
        "0" to "Winter"
    )

    //maps codes to days of the week
    val daysOfTheWeek = mapOf<String,String>(
        "U" to "Sunday",
        "M" to "Monday",
        "T" to "Tuesday",
        "W" to "Wednesday",
        "H" to "Thursday",
        "R" to "Thursday",
        "F" to "Friday",
        "S" to "Saturday"
    )

    //maps abbreviations to full campus names
    val campuses = mapOf<String,String>(
        "NB" to "New Brunswick",
        "NK" to "Newark",
        "CM" to "Camden",
        "ONLINE_NB" to "New Brunswick - Online and Remote Instruction Courses",
        "ONLINE_NK" to "Newark - Online and Remote Instruction Courses",
        "ONLINE_CM" to "Camden - Online and Remote Instruction Courses",
        "B" to "Burlington County Community College - Mt Laurel",
        "CC" to "Camden County College - Blackwood Campus",
        "H" to "County College of Morris",
        "CU" to "Cumberland County College",
        "MC" to "Denville - RU-Morris",
        "WM" to "Freehold WMHEC - RU-BCC",
        "L" to "Lincroft - RU-BCC",
        "AC" to "Mays Landing - RU-ACCC",
        "J" to "McGuire-Dix-Lakehurst RU-JBMDL",
        "D" to "Mercer County Community College",
        "RV" to "North Branch - RU-RVCC"
    )

    //maps subject codes to subject names
    val subjects = mapOf<String,String>(
        "003" to "003 - Academic Foundations",
        "005" to "005 - Academic Studies",
        "907" to "907 - Academic and Student Development",
        "010" to "010 - Accounting",
        "012" to "012 - Administration of Justice",
        "011" to "011 - Administrative Studies",
        "021" to "021 - Aerospace Engineering",
        "016" to "016 - African Studies",
        "013" to "013 - African, Middle Eastern, and South Asian Languages and Literatures",
        "014" to "014 - Africana Studies",
        "018" to "018 - Aging",
        "030" to "030 - Agricultural Economic Marketing",
        "040" to "040 - Agricultural Engineering",
        "045" to "045 - Agricultural Microbiology",
        "035" to "035 - Agricultural and Natural Resource Management",
        "020" to "020 - Agriculture and Food Systems",
        "047" to "047 - Alcohol Studies",
        "049" to "049 - American Language Studies",
        "050" to "050 - American Studies",
        "055" to "055 - Anatomy",
        "059" to "059 - Ancient Mediterranean Civilizations",
        "060" to "060 - Ancient and Medieval Civilizations",
        "064" to "064 - Anesthesiology",
        "065" to "065 - Animal Pathology",
        "067" to "067 - Animal Science",
        "070" to "070 - Anthropology",
        "642" to "642 - Applied Mathematics",
        "755" to "755 - Applied Physics",
        "076" to "076 - Applied Psychology",
        "073" to "073 - Applied Science",
        "074" to "074 - Arabic Languages",
        "075" to "075 - Archaeology",
        "078" to "078 - Armenian",
        "080" to "080 - Art",
        "081" to "081 - Art",
        "082" to "082 - Art History",
        "084" to "084 - Arts Management",
        "090" to "090 - Arts and Sciences",
        "083" to "083 - Arts, Culture, and Media",
        "098" to "098 - Asian Studies",
        "100" to "100 - Astronomy",
        "105" to "105 - Astrophysics",
        "107" to "107 - Atmospheric Science",
        "110" to "110 - Bacteriology",
        "112" to "112 - Behavioral and Neural Sciences",
        "115" to "115 - Biochemistry",
        "116" to "116 - Bioenvironmental Engineering",
        "117" to "117 - Bioenvironmental Engineering",
        "119" to "119 - Biological Sciences",
        "120" to "120 - Biology",
        "123" to "123 - Biology - Environmental",
        "121" to "121 - Biology: Computational and Integrative",
        "118" to "118 - Biomaps",
        "133" to "133 - Biomaterials and Biofabrication",
        "122" to "122 - Biomathematics",
        "125" to "125 - Biomedical Engineering",
        "139" to "139 - Biomedical Informatics",
        "132" to "132 - Biomedical Sciences",
        "124" to "124 - Biomedical Technology",
        "127" to "127 - Bioresource Engineering",
        "114" to "114 - Biostatistics",
        "126" to "126 - Biotechnology",
        "130" to "130 - Botany",
        "131" to "131 - Botany and Plant Physiology",
        "135" to "135 - Business Administration",
        "141" to "141 - Business Analytics",
        "136" to "136 - Business Analytics and Information Technology",
        "142" to "142 - Business Communication",
        "140" to "140 - Business Law",
        "137" to "137 - Business and Science",
        "134" to "134 - Business of Fashion",
        "138" to "138 - Business, International",
        "145" to "145 - Catalan",
        "146" to "146 - Cell Biology and Neuroscience",
        "148" to "148 - Cell and Developmental Biology",
        "149" to "149 - Central and East European Studies",
        "150" to "150 - Ceramic and Materials Engineering",
        "158" to "158 - Chemical Biology",
        "155" to "155 - Chemical and Biochemical Engineering",
        "160" to "160 - Chemistry",
        "163" to "163 - Childhood Studies",
        "165" to "165 - Chinese",
        "170" to "170 - Chinese Studies",
        "175" to "175 - Cinema Studies",
        "178" to "178 - Civic Engagement",
        "180" to "180 - Civil and Environmental Engineering",
        "190" to "190 - Classics",
        "191" to "191 - Clinical Laboratory Science",
        "821" to "821 - Clinical Psychology",
        "183" to "183 - Clinical and Translational Sciences",
        "185" to "185 - Cognitive Science",
        "186" to "186 - College Teaching",
        "187" to "187 - College and University Leadership",
        "192" to "192 - Communication",
        "194" to "194 - Communication and Information Studies",
        "189" to "189 - Communication and Media Studies",
        "193" to "193 - Community Health Outreach",
        "195" to "195 - Comparative Literature",
        "197" to "197 - Computational Biology",
        "198" to "198 - Computer Science",
        "196" to "196 - Computer and Information Sciences",
        "199" to "199 - Cooperative Education",
        "188" to "188 - Core Courses",
        "200" to "200 - Creative Writing",
        "202" to "202 - Criminal Justice",
        "206" to "206 - Dance",
        "203" to "203 - Dance",
        "207" to "207 - Dance Education",
        "219" to "219 - Data Science",
        "201" to "201 - Dental Public Health",
        "213" to "213 - Dental Science",
        "205" to "205 - Dermatology",
        "208" to "208 - Design",
        "209" to "209 - Digital Studies",
        "218" to "218 - Earth System Science",
        "214" to "214 - East Asian Languages and Area Studies",
        "217" to "217 - East Asian Languages and Cultures",
        "215" to "215 - Ecology",
        "704" to "704 - Ecology and Natural Resources",
        "216" to "216 - Ecology, Evolution and Natural Resources",
        "220" to "220 - Economics",
        "223" to "223 - Economics, Applied",
        "300" to "300 - Education",
        "233" to "233 - Education-Adult and Continuing Ed",
        "245" to "245 - Education-College Student Affairs",
        "297" to "297 - Education-Counseling Psychology",
        "298" to "298 - Education-Counseling Psychology and Dev",
        "259" to "259 - Education-Creative Arts Ed",
        "270" to "270 - Education-Creative Arts Ed",
        "320" to "320 - Education-Curriculum Instruction",
        "262" to "262 - Education-Design of Learning Contexts",
        "251" to "251 - Education-Early Child/Elementary Ed",
        "291" to "291 - Education-Educ Stats and Measurementnt",
        "255" to "255 - Education-Education Electives",
        "265" to "265 - Education-Education, Culture, and Society",
        "230" to "230 - Education-Educational Admin, Superv, and Adult Ed",
        "260" to "260 - Education-Educational Leadership",
        "290" to "290 - Education-Educational Psychology",
        "240" to "240 - Education-Elementary Ed",
        "252" to "252 - Education-English/Language Arts Ed",
        "250" to "250 - Education-General Elective",
        "294" to "294 - Education-Gifted Education",
        "507" to "507 - Education-Higher Education",
        "253" to "253 - Education-Language Ed",
        "295" to "295 - Education-Learning, Cognition, and Dev",
        "618" to "618 - Education-Literacy Education",
        "254" to "254 - Education-Mathematics Ed",
        "296" to "296 - Education-Medical and Health Professions",
        "299" to "299 - Education-Reading",
        "256" to "256 - Education-Science Ed",
        "257" to "257 - Education-Social Ed",
        "310" to "310 - Education-Social and Philosoph Found of Ed",
        "293" to "293 - Education-Special Education",
        "267" to "267 - Education-Teacher Leadership",
        "288" to "288 - Education-Vocational/Technical Ed",
        "364" to "364 - Educational Opportunity Fund",
        "322" to "322 - Elective Course",
        "330" to "330 - Electrical Engineering",
        "332" to "332 - Electrical and Computer Engineering",
        "333" to "333 - Electrical and Computer Engineering",
        "325" to "325 - Emergency Medicine",
        "340" to "340 - Endocrinology and Animal Biosciences",
        "335" to "335 - Energy",
        "336" to "336 - Energy Systems",
        "346" to "346 - Energy Systems",
        "345" to "345 - Engineering Management",
        "350" to "350 - English",
        "357" to "357 - English - American Language Studies",
        "352" to "352 - English - American Literature",
        "355" to "355 - English - Composition and Writing",
        "351" to "351 - English - Creative Writing",
        "354" to "354 - English - Film Studies",
        "353" to "353 - English - Literary Theory",
        "358" to "358 - English - Literature",
        "359" to "359 - English - Theories and Methods",
        "356" to "356 - English as a Second Language",
        "370" to "370 - Entomology",
        "369" to "369 - Entomology and Economic Zoology",
        "382" to "382 - Entrepreneurship",
        "378" to "378 - Environmental Change, Human Dimension",
        "380" to "380 - Environmental Geology",
        "368" to "368 - Environmental Planning",
        "573" to "573 - Environmental Planning and Design",
        "372" to "372 - Environmental Planning and Geomatics",
        "374" to "374 - Environmental Policy, Institutions and Behavior",
        "375" to "375 - Environmental Sciences",
        "381" to "381 - Environmental Studies",
        "015" to "015 - Environmental and Biological Sciences",
        "373" to "373 - Environmental and Business Economics",
        "371" to "371 - Environmental and Occupational Health",
        "379" to "379 - Environmental and Occupational Medicine",
        "383" to "383 - Epidemiology",
        "384" to "384 - Epidemiology & Quantitative",
        "522" to "522 - Ethics in Business Environment",
        "360" to "360 - European Studies",
        "001" to "001 - Exchange",
        "376" to "376 - Exchange Registration",
        "385" to "385 - Executive MBA Program",
        "377" to "377 - Exercise Science",
        "388" to "388 - Family Health",
        "386" to "386 - Family Medicine",
        "391" to "391 - Field Work",
        "387" to "387 - Film Studies",
        "211" to "211 - Filmmaking",
        "390" to "390 - Finance",
        "430" to "430 - Financial Analysis",
        "393" to "393 - Financial Planning",
        "392" to "392 - Fisheries Science",
        "400" to "400 - Food Science",
        "395" to "395 - Food and Business Economics",
        "415" to "415 - Foreign Languages",
        "410" to "410 - Foreign Languages",
        "412" to "412 - Forensic Science",
        "420" to "420 - French",
        "443" to "443 - Gender Studies",
        "999" to "999 - General Education Requirements Met",
        "440" to "440 - General Engineering",
        "447" to "447 - Genetics",
        "448" to "448 - Genetics & Microbiology",
        "455" to "455 - Geo-Spatial Information Science",
        "450" to "450 - Geography",
        "460" to "460 - Geological Sciences",
        "470" to "470 - German",
        "478" to "478 - Global Affairs",
        "475" to "475 - Global Sport Business",
        "480" to "480 - Global Studies",
        "485" to "485 - Graduate - Newark",
        "486" to "486 - Graduate Student Professional Development",
        "085" to "085 - Graphic Design",
        "490" to "490 - Greek",
        "489" to "489 - Greek, Modern",
        "501" to "501 - Health Administration",
        "492" to "492 - Health Care Organization & Admin",
        "503" to "503 - Health Communication and Information",
        "498" to "498 - Health Education & Behavioral Sciences",
        "493" to "493 - Health Education and Behavioral Sciences",
        "504" to "504 - Health Information Management",
        "502" to "502 - Health Outcomes, Policy and Economics",
        "491" to "491 - Health Policy & Admin.",
        "499" to "499 - Health Sciences",
        "497" to "497 - Health Services Management",
        "494" to "494 - Health Systems and Policy",
        "496" to "496 - Health, Physical Ed, Sport, Rec",
        "495" to "495 - Health, Physical Education and Sport Studies",
        "500" to "500 - Hebraic Studies",
        "505" to "505 - Hindi",
        "509" to "509 - Historical Methods and Skills",
        "510" to "510 - History",
        "508" to "508 - History - Africa, Asia, Latin America",
        "512" to "512 - History - American",
        "513" to "513 - History - French",
        "506" to "506 - History - General/Comparative",
        "516" to "516 - History - World: African, Asian, Latin American",
        "520" to "520 - Home Economics",
        "526" to "526 - Honors Living-Learning Community",
        "525" to "525 - Honors Program",
        "530" to "530 - Horticulture",
        "537" to "537 - Hospitality Management",
        "532" to "532 - Human Ecology",
        "533" to "533 - Human Resource Management",
        "531" to "531 - Human-Computer Interaction",
        "534" to "534 - Humanities",
        "535" to "535 - Hungarian",
        "545" to "545 - Industrial Relations and Human Resources",
        "540" to "540 - Industrial and Systems Engineering",
        "543" to "543 - Informatics",
        "548" to "548 - Information Systems",
        "544" to "544 - Information Technology",
        "547" to "547 - Information Technology and Informatics",
        "908" to "908 - Instruction and Research Development",
        "546" to "546 - Integrative Neuroscience",
        "557" to "557 - Interdisciplinary - Mason Gross",
        "555" to "555 - Interdisciplinary Studies",
        "554" to "554 - Interdisciplinary Studies",
        "556" to "556 - Interdisciplinary Studies - Arts and Sciences",
        "621" to "621 - Interfunctional",
        "553" to "553 - International Business",
        "552" to "552 - International Business and Business Environment",
        "559" to "559 - International Environmental Studies",
        "558" to "558 - International Studies",
        "549" to "549 - International Studies",
        "560" to "560 - Italian",
        "565" to "565 - Japanese",
        "561" to "561 - Jazz History and Research",
        "563" to "563 - Jewish Studies",
        "570" to "570 - Journalism",
        "086" to "086 - Journalism - Newark",
        "571" to "571 - Journalism and Mass Media",
        "567" to "567 - Journalism and Media Studies",
        "564" to "564 - Justice Studies",
        "204" to "204 - Justice Studies",
        "572" to "572 - Kinesiology and Applied Physiology",
        "574" to "574 - Korean",
        "575" to "575 - Labor Studies",
        "578" to "578 - Labor Studies and Employment Relations",
        "579" to "579 - Labor and Employment Relations",
        "550" to "550 - Landscape Architecture",
        "617" to "617 - Languages and Cultures",
        "580" to "580 - Latin",
        "590" to "590 - Latin American Studies",
        "595" to "595 - Latino and Hispanic Caribbean Studies",
        "600" to "600 - Law",
        "601" to "601 - Law",
        "602" to "602 - Law, Professional Skills",
        "609" to "609 - Leadership",
        "607" to "607 - Leadership Skills",
        "605" to "605 - Leadership and Management",
        "606" to "606 - Liberal Studies",
        "611" to "611 - Library Service, Professional Improvement",
        "610" to "610 - Library and Information Science",
        "615" to "615 - Linguistics",
        "616" to "616 - Literatures in English",
        "620" to "620 - Management",
        "625" to "625 - Management Information Systems",
        "623" to "623 - Management Science and Information Systems",
        "624" to "624 - Management and Work",
        "626" to "626 - Managerial Economics",
        "628" to "628 - Marine Sciences",
        "629" to "629 - Marine and Coastal Sciences",
        "630" to "630 - Marketing",
        "631" to "631 - Mason Gross - Arts Online",
        "632" to "632 - Mason Gross - Digital Filmmaking",
        "635" to "635 - Materials Science and Engineering",
        "636" to "636 - Materials Science and Engineering",
        "645" to "645 - Mathematical Science",
        "643" to "643 - Mathematics",
        "640" to "640 - Mathematics",
        "644" to "644 - Mathematics for Teachers",
        "650" to "650 - Mechanical and Aerospace Engineering",
        "654" to "654 - Mechanics",
        "655" to "655 - Mechanics and Materials Science",
        "657" to "657 - Media Studies",
        "652" to "652 - Medical Ethics and Policy",
        "658" to "658 - Medical Imaging",
        "660" to "660 - Medical Technology",
        "663" to "663 - Medicinal Chemistry",
        "665" to "665 - Medicine",
        "667" to "667 - Medieval Studies",
        "670" to "670 - Meteorology",
        "682" to "682 - Microbial Biology",
        "680" to "680 - Microbiology",
        "681" to "681 - Microbiology and Molecular Genetics",
        "683" to "683 - Middle Eastern Language and Area Studies",
        "685" to "685 - Middle Eastern and Islamic Studies",
        "687" to "687 - Migration Studies: Policy, Health and Governance",
        "690" to "690 - Military Education, Air Force",
        "691" to "691 - Military Education, Army",
        "692" to "692 - Military Education, Navy",
        "693" to "693 - Molecular Biology",
        "694" to "694 - Molecular Biology and Biochemistry",
        "696" to "696 - Molecular Biophysics",
        "695" to "695 - Molecular Biosciences",
        "697" to "697 - Molecular and Cell Biology",
        "689" to "689 - Multidiscipline",
        "698" to "698 - Museum Studies",
        "702" to "702 - Music",
        "700" to "700 - Music",
        "087" to "087 - Music - Newark",
        "679" to "679 - Music Technology",
        "703" to "703 - Music, Applied",
        "701" to "701 - Music, Applied",
        "414" to "414 - Natural Resource Management",
        "710" to "710 - Neuroscience",
        "699" to "699 - Nonprofit Leadership",
        "706" to "706 - North American Indian Studies",
        "705" to "705 - Nursing",
        "708" to "708 - Nursing - RN",
        "707" to "707 - Nutrition",
        "709" to "709 - Nutritional Sciences",
        "719" to "719 - OB/Gyn",
        "712" to "712 - Oceanography",
        "716" to "716 - Operations Management",
        "711" to "711 - Operations Research",
        "722" to "722 - Ophthalmology",
        "829" to "829 - Organizational Behavior",
        "713" to "713 - Organizational Leadership",
        "726" to "726 - Orthopedic Surgery",
        "731" to "731 - Packaging Engineering",
        "727" to "727 - Pathology",
        "736" to "736 - Peace Corps Masters International",
        "735" to "735 - Peace and Conflict Studies",
        "724" to "724 - Pediatrics",
        "714" to "714 - Perceptual Science",
        "723" to "723 - Persian",
        "715" to "715 - Pharmaceutical Chemistry",
        "721" to "721 - Pharmaceutics",
        "717" to "717 - Pharmacognosy",
        "728" to "728 - Pharmacology",
        "718" to "718 - Pharmacology, Cellular and Molecular",
        "720" to "720 - Pharmacy",
        "725" to "725 - Pharmacy Practice and Administration",
        "732" to "732 - Pharmacy and Toxicology",
        "730" to "730 - Philosophy",
        "740" to "740 - Physical Education",
        "759" to "759 - Physical Medicine and Rehab",
        "742" to "742 - Physical Therapy",
        "745" to "745 - Physician Assistant",
        "750" to "750 - Physics",
        "760" to "760 - Physiology",
        "761" to "761 - Physiology and Integrative Biology",
        "763" to "763 - Physiology and Neurobiology",
        "762" to "762 - Planning and Public Policy",
        "765" to "765 - Plant Biology",
        "770" to "770 - Plant Pathology",
        "780" to "780 - Plant Physiology",
        "776" to "776 - Plant Science",
        "778" to "778 - Plant Science and Technology",
        "785" to "785 - Police Science",
        "775" to "775 - Policy, Health, and Administration",
        "787" to "787 - Polish",
        "790" to "790 - Political Science",
        "800" to "800 - Pomology",
        "810" to "810 - Portuguese",
        "812" to "812 - Portuguese and Lusophone World Studies",
        "002" to "002 - Pre-Pharmacy",
        "792" to "792 - Prevention Science",
        "835" to "835 - Professional Accounting",
        "813" to "813 - Professional Development",
        "815" to "815 - Professional Occupational Education",
        "820" to "820 - Professional Psychology",
        "817" to "817 - Professional Studies",
        "819" to "819 - Psychiatric Rehabilitation and Psychology",
        "825" to "825 - Psychiatry",
        "828" to "828 - Psychiatry",
        "830" to "830 - Psychology",
        "844" to "844 - Psychology, Applied",
        "834" to "834 - Public Administration",
        "843" to "843 - Public Administration and Management",
        "831" to "831 - Public Administration, Executive",
        "824" to "824 - Public Affairs",
        "832" to "832 - Public Health",
        "822" to "822 - Public Health Core",
        "823" to "823 - Public Health Elective",
        "816" to "816 - Public Informatics",
        "833" to "833 - Public Policy",
        "827" to "827 - Public Service",
        "836" to "836 - Puerto Rican Studies",
        "848" to "848 - Quantitative Biomedicine",
        "839" to "839 - Quantitative Finance",
        "837" to "837 - Quantitative Methods",
        "845" to "845 - Quantitative Methods",
        "855" to "855 - Quaternary Studies",
        "838" to "838 - Radiation Science",
        "851" to "851 - Real Estate",
        "846" to "846 - Recreation and Leisure Services",
        "840" to "840 - Religion",
        "841" to "841 - Religious Studies",
        "847" to "847 - Research - Public Health",
        "842" to "842 - Rhetoric",
        "850" to "850 - Rural Sociology",
        "860" to "860 - Russian",
        "859" to "859 - Russian Central and European Studies",
        "902" to "902 - SEBS Internship",
        "870" to "870 - Sanitary Engineering",
        "868" to "868 - Scheduling",
        "826" to "826 - School Psychology",
        "890" to "890 - Science",
        "885" to "885 - Science and Technology Management",
        "880" to "880 - Science, Technology and Society",
        "900" to "900 - Secretarial Studies",
        "888" to "888 - Sexualities Studies",
        "861" to "861 - Slavic and East European Studies",
        "904" to "904 - Social Justice",
        "905" to "905 - Social Sciences",
        "910" to "910 - Social Work",
        "920" to "920 - Sociology",
        "930" to "930 - Soils and Crops",
        "925" to "925 - South Asian Studies",
        "940" to "940 - Spanish",
        "950" to "950 - Speech",
        "614" to "614 - Speech and Hearing Sciences in Linguistics",
        "953" to "953 - Speech, Language and Hearing Sciences",
        "955" to "955 - Sport Management",
        "960" to "960 - Statistics",
        "954" to "954 - Statistics - Data Science",
        "958" to "958 - Statistics, Financial and Risk Managemen",
        "959" to "959 - Study Abroad",
        "794" to "794 - Supply Chain Analytics",
        "795" to "795 - Supply Chain Management",
        "799" to "799 - Supply Chain and Marketing Science",
        "945" to "945 - Surgery",
        "956" to "956 - Swahili",
        "962" to "962 - Taxation",
        "964" to "964 - Teacher Preparation",
        "957" to "957 - Teaching Assistant",
        "965" to "965 - Theater",
        "088" to "088 - Theater - Newark",
        "966" to "966 - Theater Arts",
        "961" to "961 - Thesis",
        "963" to "963 - Toxicology",
        "973" to "973 - Turkish",
        "974" to "974 - Twi",
        "967" to "967 - Ukranian",
        "968" to "968 - Urban & Environmental Health",
        "969" to "969 - Urban Health Administration",
        "971" to "971 - Urban Planning and Design",
        "970" to "970 - Urban Planning and Policy Development",
        "978" to "978 - Urban Secondary Education",
        "975" to "975 - Urban Studies and Community Development",
        "977" to "977 - Urban Systems",
        "980" to "980 - Vegetable Crops",
        "089" to "089 - Video Production - Newark",
        "982" to "982 - Walt Whitman Studies",
        "985" to "985 - Wildlife Conservation",
        "988" to "988 - Women's, Gender, and Sexuality Studies",
        "991" to "991 - World Languages",
        "989" to "989 - Writing",
        "990" to "990 - Zoology"
    )

    /**
     * Provides a map for the year field in the prompt
     * @return a map of every year from 2021 to current year + 1 as a string to itself
     */
    fun getYearMap(): Map<String,String> {
        val map = mutableMapOf<String,String>()
        for (year in 2021..Calendar.getInstance().get(Calendar.YEAR) + 1) {
            map[year.toString()] = year.toString()
        }
        return map
    }
}