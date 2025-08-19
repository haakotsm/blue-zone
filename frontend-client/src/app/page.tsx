'use client'

import { useState, useEffect } from 'react'
import OrderMonitor from '../components/OrderMonitor'
import ServiceStatus from '../components/ServiceStatus'

export default function Home() {
  const [orders, setOrders] = useState([])
  const [serviceStatuses, setServiceStatuses] = useState({
    orderService: 'unknown',
    paymentService: 'unknown',
    inventoryService: 'unknown',
    notificationService: 'unknown'
  })

  useEffect(() => {
    // Fetch initial data
    fetchOrders()
    checkServiceStatuses()

    // Set up polling for real-time updates
    const orderInterval = setInterval(fetchOrders, 5000)
    const statusInterval = setInterval(checkServiceStatuses, 10000)

    return () => {
      clearInterval(orderInterval)
      clearInterval(statusInterval)
    }
  }, [])

  const fetchOrders = async () => {
    try {
      const response = await fetch('http://localhost:8081/api/orders')
      if (response.ok) {
        const data = await response.json()
        setOrders(data)
      }
    } catch (error) {
      console.error('Error fetching orders:', error)
    }
  }

  const checkServiceStatuses = async () => {
    const services = [
      { name: 'orderService', url: 'http://localhost:8081/actuator/health' },
      { name: 'paymentService', url: 'http://localhost:8082/actuator/health' },
      { name: 'inventoryService', url: 'http://localhost:8083/actuator/health' },
      { name: 'notificationService', url: 'http://localhost:8084/actuator/health' }
    ]

    const statuses: { [key: string]: string } = {}
    
    for (const service of services) {
      try {
        const response = await fetch(service.url)
        statuses[service.name] = response.ok ? 'healthy' : 'unhealthy'
      } catch (error) {
        statuses[service.name] = 'down'
      }
    }

    setServiceStatuses(statuses as typeof serviceStatuses)
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-7xl mx-auto">
        <h1 className="text-4xl font-bold text-gray-900 mb-8 text-center">
          Blue Zone Ordering System Monitor
        </h1>
        
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          <div className="lg:col-span-2">
            <OrderMonitor orders={orders} />
          </div>
          
          <div>
            <ServiceStatus statuses={serviceStatuses} />
          </div>
        </div>
      </div>
    </div>
  )
}
