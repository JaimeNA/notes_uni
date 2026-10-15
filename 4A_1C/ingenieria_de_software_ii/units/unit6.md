# Unidad 6:????

## Residuality theory

The problem: why is software architecture so difficult? The three fundamental things are:

- Time
- Change over time 
- Uncertainity about change 

Those are things that cannot be represented and that are vital in an architecture since it changes 
over time.

Another one: what is the unit of SWE?

Physics have atoms and particles, but SWE have time, and is cannot be represented. 

- Some systems are ordered 
- Some systems are disordered <-- here is where the problem appears 

In SWE we put an ordered system inside a disordered system, and that enviroment is what 
tears the architecture apart. The enviroment can be made smaller, but reality never goes away, 
that's called technical debt. 

### Complexity science 

- Random simulations
- Kauffman networks(a.k.a. Random Boolean Network)

All we do as software engineers is a two-step algorithm:

1. Create a random simulation 
2. Do a NKP analysis on it 

### Residuality theory 

Make the simulation more random and make the NKP analysis explicit, it surfaces what we already do 
as architects and make it better. It's ar hearth a phylisophical idea.
After something happends to the complex system, it changes, the result of this is called a 
**residue**. And something, unknown, will happend to the residue. We can't predict the future, but 
we can control de residue. 

So, we need to check, before building, whats left after some attractor, what survives of the 
original naive architecture. We compress all these residues and the new architecture starts to 
survive, to resists different attractors. 

One residue con survive a multiple number of attractors, the same residue that was originated by 
on attractor. So, randombly simulating the architecture we build a stronger architecture. 

However, people are very bad at creating random simulations, so we tend to stick to conservative 
ideas that don't strenghten the architecture too much. We need to be as random as possible. 

There is something called the **edge of chaos**, which defines the boundary between a monolith 
system and a full microservices systems. Too much modules and its really hard to mantain and 
debug until it eventually collapses, one single failure in a monolith and the whole things goes 
down. 

### Contagion analysis 

Sometimes a stressor may affect two nodes that are completaly unrelated, that means that they 
are related, but we didn't see it. This is known as coupling.



