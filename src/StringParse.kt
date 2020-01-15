fun main() {
    val headers = "Content-Disposition: attachment; filename=\"filename.jpg\""

    println(headers.split("=")[1].replace("\"", ""))
}