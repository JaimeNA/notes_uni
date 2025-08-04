# Multiple slaves

The esp32-c6 only has one SPI bus avaiable and we need the AP to be able to speak to 4 slaves via that bus. This is achieved using an interrupts system, each slave has an INTR and a CS pin. The master is responsible for managing these interrupts and interact with the slaves. 
