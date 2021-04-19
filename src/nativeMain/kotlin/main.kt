import pigpio.*

const val GPIO_LED = 25

fun main() {
    println("Start blinking LED")

    initGPIO()
    blinkLed()
}

private fun initGPIO() {
    if (gpioInitialise() < 0) {
        println("GPIO Error initialising")
        return
    }
}

private fun blinkLed() {
    val ledPort = GPIO_LED.toUInt()
    initPortWithMode(ledPort, PI_OUTPUT)

    var blinkCount = 30000
    while (blinkCount > 0) {
        println("BLINK")
        gpioWrite(ledPort, PI_LOW)
        gpioSleep(PI_TIME_RELATIVE, 0, 500000)
        gpioWrite(ledPort, PI_HIGH)
        gpioSleep(PI_TIME_RELATIVE, 0, 500000)
        blinkCount--
    }
}

private fun initPortWithMode(port: UInt, mode: Int) {
    if (gpioSetMode(port, mode.toUInt()) < 0) {
        println("Could not set mode for GPIO$port")
        return
    }
}