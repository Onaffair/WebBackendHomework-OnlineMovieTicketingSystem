const { defineConfig } = require('@vue/cli-service')

const os = require('os');

function getLocalIP() {
  const interfaces = os.networkInterfaces();
  for (const name of Object.keys(interfaces)) {
    for (const iface of interfaces[name]) {
      if (iface.family === 'IPv4' && !iface.internal) {
        return iface.address;
      }
    }
  }
  return '127.0.0.1';
}
const localIP = getLocalIP()

console.log(localIP)

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host: '0.0.0.0',
    port: 720,
    proxy: {
      '/api': {
        target: 'http://localhost:721', // 你的目标服务器地址
        changeOrigin: true, // 是否改变请求头中的Origin
        pathRewrite: { '^/api': '' } // 重写路径，去掉请求地址中的/api
      }
    }
  }
})
