import React from 'react'

interface ServiceStatusProps {
  statuses: {
    orderService: string
    paymentService: string
    inventoryService: string
    notificationService: string
  }
}

const ServiceStatus: React.FC<ServiceStatusProps> = ({ statuses }) => {
  const getStatusColor = (status: string) => {
    switch (status) {
      case 'healthy': return 'bg-green-100 text-green-800 border-green-300'
      case 'unhealthy': return 'bg-yellow-100 text-yellow-800 border-yellow-300'
      case 'down': return 'bg-red-100 text-red-800 border-red-300'
      default: return 'bg-gray-100 text-gray-800 border-gray-300'
    }
  }

  const getStatusIcon = (status: string) => {
    switch (status) {
      case 'healthy': return '✅'
      case 'unhealthy': return '⚠️'
      case 'down': return '❌'
      default: return '❓'
    }
  }

  const services = [
    { key: 'orderService', name: 'Order Service', port: '8081' },
    { key: 'paymentService', name: 'Payment Service', port: '8082' },
    { key: 'inventoryService', name: 'Inventory Service', port: '8083' },
    { key: 'notificationService', name: 'Notification Service', port: '8084' }
  ]

  return (
    <div className="bg-white rounded-lg shadow-md p-6">
      <h2 className="text-2xl font-semibold mb-4">Service Status</h2>
      
      <div className="space-y-4">
        {services.map((service) => {
          const status = statuses[service.key as keyof typeof statuses]
          return (
            <div
              key={service.key}
              className={`p-4 rounded-lg border-2 ${getStatusColor(status)}`}
            >
              <div className="flex items-center justify-between">
                <div>
                  <h3 className="font-medium">{service.name}</h3>
                  <p className="text-sm opacity-75">Port: {service.port}</p>
                </div>
                <div className="flex items-center space-x-2">
                  <span className="text-lg">{getStatusIcon(status)}</span>
                  <span className="font-medium capitalize">{status}</span>
                </div>
              </div>
            </div>
          )
        })}
      </div>
      
      <div className="mt-6 pt-4 border-t border-gray-200">
        <p className="text-sm text-gray-500">
          Last updated: {new Date().toLocaleTimeString()}
        </p>
      </div>
    </div>
  )
}

export default ServiceStatus
