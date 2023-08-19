package com.example.mycourseapp

import androidx.annotation.DrawableRes

data class Courses(
    val rating: Float,
    val title: String,
    @DrawableRes val thumbNail: Int,
    val body: String

)

val course1 = Courses(
    4.5f,
    "The Complete Android 14 Developer Course - Build 100 Apps",
    R.drawable.course1,

    "I promise You ... As i helped 800,000 students to learn android, I will help you to become another pro.."

)
val course2 = Courses(
    4.4f,
    "Mastering Android App Development with Kotlin [XML +COMPOSE]",
    R.drawable.course2,

    "You came to the right place, where you'll master android app development from zero to hero through step-by-step explanations and well-crafted curriculum to take you with no or probably few coding skills to start creating professional android apps."

)
val course3 = Courses(
    4.6f,
    "Advanced iOS App Development using Swift 5.5",
    R.drawable.course3,
    "Take your iOS development skills to the next level with this advanced course. Learn Swift 5.5, explore advanced UI/UX techniques, and build complex apps with confidence."
)

val course4 = Courses(
    4.2f,
    "Full Stack Web Development Bootcamp",
    R.drawable.course4,
    "Become a full stack web developer in this comprehensive bootcamp. From front-end technologies like HTML/CSS to back-end frameworks like Node.js and databases, we've got you covered."
)
val course5 = Courses(
    4.8f,
    "Machine Learning and Data Science Masterclass",
    R.drawable.course5,
    "Dive into the world of machine learning and data science. Learn the fundamentals of algorithms, data preprocessing, and model building, and become a proficient data scientist."
)

val course6 = Courses(
    4.7f,
    "Graphic Design Fundamentals: From Sketch to Digital Art",
    R.drawable.course6,
    "Unleash your creative potential in graphic design. This course will take you through the basics of sketching and digital design tools, enabling you to create stunning visuals."
)

val course7 = Courses(
    4.5f,
    "Introduction to Python Programming for Beginners",
    R.drawable.course7,
    "Begin your coding journey with Python. This course is designed for beginners, covering basic syntax, data structures, and problem-solving techniques, setting you on the path to becoming a programmer."
)

val allCourses = listOf<Courses>(course1, course2, course3, course4, course5, course6, course7)
