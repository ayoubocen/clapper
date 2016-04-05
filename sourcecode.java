int soundSensor = 3;
int relay = 4;
int claps = 0;
long detectionSpanInitial = 0;
long detectionSpan = 0;
boolean lightState = false;

void setup() {
    pinMode(soundSensor, INPUT);
    pinMode(relay, OUTPUT);
}

void loop() {

    int sensorState = digitalRead(soundSensor);

    if (sensorState == 0) {
        if (claps == 0) {
            detectionSpanInitial = detectionSpan = millis();
            claps++;
        } else if (claps > 0 && millis()-detectionSpan >= 50) {
            detectionSpan = millis();
            claps++;
        }
    }

    if (millis()-detectionSpanInitial >= 400) {
        if (claps == 2) {
            if (!lightState) {
                lightState = true;
                digitalWrite(relay, HIGH);
            } else if (lightState) {
                lightState = false;
                digitalWrite(relay, LOW);
            }
        }
        claps = 0;
    }
}
