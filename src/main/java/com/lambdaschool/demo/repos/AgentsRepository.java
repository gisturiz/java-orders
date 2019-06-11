package com.lambdaschool.demo.repos;

import com.lambdaschool.demo.model.Agents;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agents, Long>
{
    Agents findByAgentname(String agentname);
}
