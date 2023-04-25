# SensorML generator

Sensorml-generator is a small Java library to create SensorML documents at RBINS. It makes use of the cruise-api library, a collection of interfaces of common use in operational oceanography.
It leverages the EARS, NERC and SeaDataNet libraries and best practices.

Capacities:

- Output a SensorML document for a PhysicalSystem (platform) or hierarchy of PhysicalComponents.
- Include event history for a device (eg. calibration) based on EARS events coupled to the device 