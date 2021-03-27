import axios from 'axios'
import { Payment } from '../model/payment'
import { config } from '../config'

export default class HttpService {
  async postPayments (payments: Payment[]) {
    return await axios.post(config.backend.host + '/api/payment', payments, {
      auth: config.backend.auth
    })
  }

  async getHistory () {
    return await axios.get(config.backend.host + '/api/history', {
      auth: config.backend.auth
    })
  }
}
