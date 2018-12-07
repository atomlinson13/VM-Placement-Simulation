# VMplacement
# Assingment for Cloud Computing
<p>Consider a data center with an infinite number of servers. Each server has 1 unit of CPU
available. Three types of VMs (virtual machines) arrive to this system. VMs of type 1 request
1/2 unit of CPU each, VMs of type 2 request 1/4 unit of CPU each, and VMs of type 3 request
1/8 unit of CPU each. In each time slot, the number of VMs which arrive into the system
is binomially distributed with parameters N and λ, and each VM is of type 1, 2, or 3, with
probability 1/3 each. Consider the following two assignment policies.
  <ul>
    <li><strong>FirstFit policy:</strong> assign the VM to the lowest indexed server which can accommodate it.
  Update the available space in the server, once a VM is assigned to it.</li>
<li><strong> BestFit policy:</strong> assign the VM to the server with the least amount of space available
(among all servers which have enough space to accommodate the VM). Update the
  available space in the server, once a VM is assigned to it.</li>
</ul>    
VMs of type 1 stay in the system for a geometrically distributed amount of time with mean
10, VMs of type 2 stay in the system for a geometrically distributed amount of time with
mean 8, VMs of type 3 stay in the system for a geometrically distributed amount of time
with mean 30. Assume the following sequence of events in each time slot: departures occur
first, then arrivals occur, followed by assignment of VMs to servers.
<ul>
<li>Assume λ = 0.9. For different values of N, N = 10, N = 100, and N = 1000, compute
the average number of servers used by simulating FirstFit policy for 106
time slots.
<li>Assume λ = 0.9. For different values of N, N = 10, N = 100, and N = 1000, compute
the average number of servers used by simulating BestFit policy for 106
time slots.
<li>Plot the average number of servers versus N under two policies, respectively. (in one
plot)
</ul>
</p>

# Output:
<img src="cc1.png">
