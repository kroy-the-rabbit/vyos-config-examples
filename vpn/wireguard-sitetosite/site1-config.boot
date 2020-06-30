interfaces {
    ethernet eth0 {
        address dhcp
    }
    ethernet eth1 {
        address 172.16.22.1/24
    }
    loopback lo {
    }
    wireguard wg0 {
        address 10.32.47.2/31
        description "Site to Site to Site2"
        peer SITE2 {
            address 10.21.21.70
            allowed-ips 10.32.47.2/31
            allowed-ips 192.168.34.0/24
            allowed-ips 172.16.22.0/24
            persistent-keepalive 15
            port 2224
            pubkey CLN0A47cOZxmiibKvj+f9uYcOWP2vPJn/xg5ujsFbmA=
        }
        port 2224
        private-key site2
    }
}
protocols {
    static {
        route 192.168.34.0/24 {
            next-hop 10.32.47.3 {
            }
        }
    }
}
service {
    ssh {
        port 22
    }
}
system {
    config-management {
        commit-revisions 100
    }
    console {
        device ttyS0 {
            speed 115200
        }
    }
    host-name vyos01
    login {
        user vyos {
            authentication {
                encrypted-password $6$MFxEUklPz$a0A2KIlCEpEV8ZOC6HLz7J6GGaK7uTw5kQQUcEIXGTqx72wGCIbPPdc/SOyJpQzextNd2rfJb5PBEA4j3.Te21
                plaintext-password ""
            }
        }
    }
    ntp {
        server 0.pool.ntp.org {
        }
        server 1.pool.ntp.org {
        }
        server 2.pool.ntp.org {
        }
    }
    syslog {
        global {
            facility all {
                level info
            }
            facility protocols {
                level debug
            }
        }
    }
}


// Warning: Do not remove the following line.
// vyos-config-version: "broadcast-relay@1:cluster@1:config-management@1:conntrack@1:conntrack-sync@1:dhcp-relay@2:dhcp-server@5:dhcpv6-server@1:dns-forwarding@2:firewall@5:https@2:interfaces@10:ipoe-server@1:ipsec@5:l2tp@3:lldp@1:mdns@1:nat@5:ntp@1:pppoe-server@3:pptp@2:qos@1:quagga@6:salt@1:snmp@1:ssh@1:sstp@2:system@16:vrrp@2:vyos-accel-ppp@2:wanloadbalance@3:webgui@1:webproxy@2:zone-policy@1"
// Release version: 1.3-kroy-202006021942
