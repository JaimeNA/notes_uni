# Multiple slaves

The esp32-c6 only has one SPI bus avaiable and we need the AP to be able to speak to 4 slaves via that bus. This is achieved using an interrupts system, each slave has an INTR and a CS pin. The master is responsible for managing these interrupts and interact with the slaves. 

## Possible implementation in the i4a codebase

Most of the codebase can remain intact, changes should be made in `ring_link`, mainly the `lowlevel` part. 
Additionally, changes the the `spi` utils should be made for the start thopology, right now it only supports transmission due to the token ring thopology.
