# i4a documentation

## Main components

```
i4a
├─ extra
│   ├─ callback
│   ├─ config
│   ├─ control
│   ├─ event
│ 
├─ internal
│   ├─ config
│   ├─ heartbeat
│   ├─ lwip_custom_hooks
│   ├─ physim
│   ├─ ring_link
│   ├─ ring_link_internal
│   ├─ ring_link_lowlevel
│   ├─ ring_link_netif
│   ├─ route
│   ├─ spi
│   ├─ utils
│   ├─ wifi
│ 
├─ routing
│   ├─ os
│   ├─ ring_share
│   ├─ routing
│   ├─ routing_config
│   ├─ shared_state
│   ├─ siblings
│   ├─ sync
│   ├─ wireless
│ 
├─ wireless
    ├─ access_point
    ├─ client
    ├─ device
    ├─ server
    ├─ shared_memory
    ├─ station
```

## Internal

Contains SPI communication and routing modules for inter-node communication.

### Config

Chip setup and config options, includes:

- Orientation(N, S, E, W, none)
- Mode(peer link, access point, root, none)
- ID(N, S, E, W, C, none, any, all)
- RX IP
- TX IP

### Heartbeat

Heartbeat payload module, the payload is broadcast periodically by each board, if  a board fails to transmit the heartbeat it will be considered as "out".

### Ring link

SPI communication setup, has 3 main queues for each chip depending on the payload type:
- Internal
- Network interface
- Low level

The main loop checks the lowlevel queue and forwards the payload to the other two if necessary.

#### Internal

Transmits and recieves payloads between nodes, 
if it recieves a payload for another station or the AP it will foward it into the ring.

#### Network interface

Transmits and recieves payloads via the network, only the AP will need to use this queue.

#### Low level

Transmits and recieves payloads via the SPI ring. Used by netif and internal.
